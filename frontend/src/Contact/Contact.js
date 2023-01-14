import './Contact.css'
import { useEffect, useRef, useState } from 'react';
import emailjs from '@emailjs/browser';
import AppNavbar from '../AppNavbar';

const Contact = () => {

    const refForm = useRef()

    useEffect(() => {
        setTimeout(() => {
            
        }, 4000);
    }, [])

    const sendEmail = (e) => {
        e.preventDefault()

        emailjs.sendForm('service_iuddaze', 'template_zbn2p1r', refForm.current, 'j2fthFWtJGz_9R4Rn')

        .then(
            () => {
                alert('Message successfully sent!')
                window.location.reload(false)
            },
            () => {
                alert('Failed to send the message, try again')
            }
        )
    }

    return (
        <>
        <AppNavbar />
        <div className='container contact-page'>
        <div className='text-zone'>
            <h1>
                Contact Us
            </h1>
        <p>
            Feel free to contact me about anything you want!
        </p>
        
        <div className='contact-form'>
            <form ref={refForm} onSubmit={sendEmail}>
                <ul>
                    <li className='half'>
                        <input type="text" name='name' placeholder='Name' required />
                    </li>
                    <li className='half'>
                        <input type="email" name='email' placeholder='Email' required />
                    </li>
                    <li>
                        <input placeholder="Subject" type="text" name="subject" required />
                    </li>
                    <li>
                        <textarea placeholder='Message' name='message' required></textarea>
                    </li>
                    <li>
                        <input type="submit" className='flat-button' value="SEND" />
                    </li>
                </ul>
            </form>
        </div>
        </div>
        </div>
        </>
    )
}

export default Contact