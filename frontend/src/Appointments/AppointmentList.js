import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';
import PatientDropdown from '../Patients/PatientDropdown';
import DoctorDropdown from '../Doctors/DoctorDropdown';

class AppointmentList extends Component {
    
    constructor(props) {
        super(props);
        this.state = {appointments: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/appointment')
            .then(response => response.json())
            .then(data => this.setState({appointments: data}));
    }

    async remove(id) {
        await fetch(`/appointment/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedAppointments = [...this.state.appointments].filter(i => i.id !== id);
            this.setState({appointments: updatedAppointments});
        });
    }

    render() {
        const {appointments, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const appointmentList = appointments.map(appointment => {
            return <tr key={appointment.id}>
                <td style={{whiteSpace: 'nowrap'}}>{appointment.dateTime}</td>
                <td>{appointment.duration}</td>
                <td>{appointment.appointmentDesc}</td>
                <td>{appointment.doctor.name} {appointment.doctor.surname}</td>
                <td>{appointment.patient.name} {appointment.patient.surname}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/appointment/" + appointment.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(appointment.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return (
            <div>
                <AppNavbar/>
                <br />
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/appointment/save">Add Appointment</Button>
                    </div>
                    <h3>Appointments</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Date and Time</th>
                            <th width="10%">Duration</th>
                            <th width="30%">Description</th>
                            <th width="10%">Doctor</th>
                            <th width="10%">Patient</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {appointmentList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default AppointmentList;