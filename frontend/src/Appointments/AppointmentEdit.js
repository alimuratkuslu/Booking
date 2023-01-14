import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import DoctorDropdown from '../Doctors/DoctorDropdown';
import PatientDropdown from '../Patients/PatientDropdown';

class DoctorEdit extends Component {

    emptyItem = {
        dateTime: '',
        duration: '',
        appointmentDesc: '',
        doctorId: '',
        patientId: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const appointment = await (await fetch(`/appointment/${this.props.match.params.id}`)).json();
            this.setState({item: appointment});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        await fetch('/appointment' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/appointment');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Appointment' : 'Add Appointment'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Date and Time</Label>
                        <Input type="text" name="dateTime" id="dateTime" value={item.dateTime || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Duration</Label>
                        <Input type="text" name="duration" id="duration" value={item.duration || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Description</Label>
                        <Input type="text" name="appointmentDesc" id="appointmentDesc" value={item.appointmentDesc || ''}
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>

                    <br/>
                    <FormGroup>
                        <Label>
                            <DoctorDropdown />
                        </Label>
                    </FormGroup>
                    <br/>
                    <FormGroup>
                        <Label>
                            <PatientDropdown />
                        </Label>
                    </FormGroup>
                    <br/>
                    <FormGroup>
                        <Label for="name">Doctor</Label>
                        <Input type="text" name="doctorId" id="doctorId" value={item.doctorId || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Patient</Label>
                        <Input type="text" name="patientId" id="patientId" value={item.patientId || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/appointment">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(DoctorEdit);