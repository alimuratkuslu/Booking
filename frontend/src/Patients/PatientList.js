import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';
import Checkbox from '../Checkbox';

class PatientList extends Component {

    constructor(props) {
        super(props);
        this.state = {patients: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/patient')
            .then(response => response.json())
            .then(data => this.setState({patients: data}));
    }

    async remove(id) {
        await fetch(`/patient/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedPatients = [...this.state.patients].filter(i => i.id !== id);
            this.setState({patients: updatedPatients});
        });
    }

    render() {
        const {patients, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const patientList = patients.map(patient => {
            return <tr key={patient.id}>
                <td style={{whiteSpace: 'nowrap'}}>{patient.name}</td>
                <td>{patient.surname}</td>
                <td>{patient.email}</td>
                <td>{patient.birthDate}</td>
                <Checkbox value={patient.isActive} />
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/patient/" + patient.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(patient.id)}>Delete</Button>
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
                        <Button color="success" tag={Link} to="/patient/save">Add Patient</Button>
                    </div>
                    <h3>Patients</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Surname</th>
                            <th width="20%">Email</th>
                            <th width="10%">Birth Date</th>
                            <th width="10%">Status</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {patientList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default PatientList;