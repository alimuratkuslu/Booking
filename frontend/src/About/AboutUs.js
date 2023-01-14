import React from 'react';
import AppNavbar from '../AppNavbar';
import './AboutUs.css';

export default function AboutUs() {
  return (
    <div>
      <AppNavbar />
      <br />
      <h1>About Us</h1>
      <h2>"Welcome to our hospital booking website!</h2>

      <p>We are a team of dedicated healthcare professionals committed to providing our patients with the highest quality of care and service.</p>
      <p>Our website was designed with the goal of making the process of booking appointments with our doctors as easy and convenient as possible.</p>
      <p>With our website, you can easily search for a doctor by specialty or location, view available appointments, and book your appointment with just a few clicks.
        We pride ourselves on the quality of our doctors, who are all highly trained and experienced in their respective fields.
        Our hospital is equipped with the latest technology and staffed by a team of skilled and compassionate caregivers.
        We understand that your health is of the utmost importance and that's why we are committed to providing you with the best possible care.
        Thank you for choosing our hospital and we look forward to serving you."</p>
      <p>Thank you for choosing us, and we look forward to working with you!</p>
    </div>
  );
}


