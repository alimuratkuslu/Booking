import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import { Link } from "react-router-dom"

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <nav className="nav">
              <a href="/" className='site-title'>Booking</a>
              <ul className='active'>
                <li>
                    <a href="/patient">Patients</a>
                </li>
                <li>
                    <a href="/doctor">Doctors</a>
                </li>
                <li>
                    <a href="/appointment">Appointments</a>
                </li>
                <li>
                    <a href="/about">About</a>
                </li>
                <li>
                    <a href="/contact">Contact Us</a>
                </li>
                <li>
                    <a href="/profile">Profile</a>
                </li>
              </ul>
            </nav>
          )
    }
}