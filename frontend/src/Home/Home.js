import React, { useState } from 'react';
import '../App.js';
import AppNavbar from '../AppNavbar.js';
import './Home.css';
import Footer from '../Footer';


export default function Home(){

    return(
        <div>
            <AppNavbar />
            <br />
            <h2>Welcome to our hospital's online booking system!</h2>
            <br />
            <p>We understand that your time is valuable, and that's why we offer the convenience of booking appointments and admissions online.
                With our system, you can easily schedule appointments with your preferred doctor or specialist, and also book admissions for any necessary procedures.

                Our state-of-the-art facilities, cutting-edge technology and highly skilled staff ensure that you receive the best possible care.

                Thank you for choosing our hospital, we look forward to providing you with the highest level of healthcare.</p>
            <Footer />
        </div>
    )
}

