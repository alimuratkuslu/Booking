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
import Logout from './Login/Logout';
import PrivateRoute from './PrivateRoute';
import { Redirect } from 'react-router-dom';

class App extends Component {

  render() {
    return(
      <Router>
        <Switch>
          <Route path='/login' component={LoginPage} />
          <PrivateRoute exact path='/' component={Home} />
          <PrivateRoute exact path='/patient' component={PatientList} />
          <PrivateRoute exact path='/patient/:id' component={PatientEdit} />
          <PrivateRoute exact path='/doctor' component={DoctorList} />
          <PrivateRoute exact path='/doctor/:id' component={DoctorEdit} />
          <PrivateRoute exact path='/appointment' component={AppointmentList} />
          <PrivateRoute exact path='/appointment/:id' component={AppointmentEdit} />
          <PrivateRoute exact path='/about' component={AboutUs} />
          <PrivateRoute exact path='/contact' component={Contact} />
          <PrivateRoute exact path='/profile' component={Profile} />
          <Route exact path='/logout' component={Logout} />
          <Redirect to='/login' />
        </Switch>
      </Router>
    )
  }
}

export default App;
