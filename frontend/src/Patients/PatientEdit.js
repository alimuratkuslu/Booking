import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import DeactivatePatient from './DeactivatePatient';
import ActivatePatient from './ActivatePatient';

class PatientEdit extends Component {

    emptyItem = {
        name: '',
        surname: '',
        email: '',
        birthDate: ''
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
            const patient = await (await fetch(`/patient/${this.props.match.params.id}`)).json();
            this.setState({item: patient});
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
    
        await fetch('/patient' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/patient');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Patient' : 'Add Patient'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Surname</Label>
                        <Input type="text" name="surname" id="surname" value={item.surname || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Birth Date</Label>
                        <Input type="text" name="birthDate" id="birthDate" value={item.birthDate || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <br/>
                    <FormGroup>
                        <Label for="name">Password</Label>
                        <Input type="password" name="password" id="password" value={item.password || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <br/>
                    <FormGroup>
                        <Label for="status">Status</Label>
                        <DeactivatePatient patientId={item.id} />
                        <ActivatePatient patientId={item.id} />
                    </FormGroup>
                    <br/>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/patient">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(PatientEdit);