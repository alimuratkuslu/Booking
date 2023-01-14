import logo from './logo.svg';
import './App.css';
import { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './Home/Home';
import PatientEdit from './Patients/PatientEdit';
import PatientList from './Patients/PatientList';
import DoctorEdit from './Doctors/DoctorEdit';
import DoctorList from './Doctors/DoctorList';
import AppointmentEdit from './Appointments/AppointmentEdit';
import AppointmentList from './Appointments/AppointmentList';
import AboutUs from './About/AboutUs';
import Contact from './Contact/Contact';
import Profile from './Profile/Profile';

class App extends Component {

  render() {
    return(
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home} />
          <Route path='/patient' exact={true} component={PatientList} />
          <Route path='/patient/:id' component={PatientEdit} />
          <Route path='/doctor' exact={true} component={DoctorList} />
          <Route path='/doctor/:id' component={DoctorEdit} />
          <Route path='/appointment' exact={true} component={AppointmentList} />
          <Route path='/appointment/:id' component={AppointmentEdit} />
          <Route path='/about' component={AboutUs} />
          <Route path='/contact' component={Contact} />
          <Route path='/profile' component={Profile} />
        </Switch>
      </Router>
    )
  }
}

export default App;
