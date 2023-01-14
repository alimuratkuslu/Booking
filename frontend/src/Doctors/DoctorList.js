import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class DoctorList extends Component {

    constructor(props) {
        super(props);
        this.state = {doctors: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/doctor')
            .then(response => response.json())
            .then(data => this.setState({doctors: data}));
    }

    async remove(id) {
        await fetch(`/doctor/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedDoctors = [...this.state.doctors].filter(i => i.id !== id);
            this.setState({doctors: updatedDoctors});
        });
    }

    render() {
        const {doctors, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const doctorList = doctors.map(doctor => {
            return <tr key={doctor.id}>
                <td style={{whiteSpace: 'nowrap'}}>{doctor.name}</td>
                <td>{doctor.surname}</td>
                <td>{doctor.email}</td>
                <td>{doctor.field}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/doctor/" + doctor.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(doctor.id)}>Delete</Button>
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
                        <Button color="success" tag={Link} to="/doctor/save">Add Doctor</Button>
                    </div>
                    <h3>Doctors</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Surname</th>
                            <th width="20%">Email</th>
                            <th width="20%">Field</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {doctorList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default DoctorList;