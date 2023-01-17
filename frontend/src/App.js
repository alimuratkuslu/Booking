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
import LoginPage from './Login/LoginPage';

class App extends Component {

  render() {
    return(
      <Router>
        <Switch>
          <Route path='/login' component={LoginPage} />
          <Route exact path='/' component={Home} />
          <Route exact path='/patient' component={PatientList} />
          <Route exact path='/patient/:id' component={PatientEdit} />
          <Route exact path='/doctor' component={DoctorList} />
          <Route exact path='/doctor/:id' component={DoctorEdit} />
          <Route exact path='/appointment' component={AppointmentList} />
          <Route exact path='/appointment/:id' component={AppointmentEdit} />
          <Route exact path='/about' component={AboutUs} />
          <Route exact path='/contact' component={Contact} />
          <Route exact path='/profile' component={Profile} />
        </Switch>
      </Router>
    )
  }
}

export default App;
