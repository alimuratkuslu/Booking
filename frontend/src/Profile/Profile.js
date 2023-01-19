import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AppNavbar from '../AppNavbar.js';
import jwt_decode from 'jwt-decode';
import './Profile.css';

const Profile = (props) => {
    const [user, setUser] = useState({});
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const data = localStorage.getItem('token');

        console.log(jwt_decode(data).sub);
        // const data = props.accessToken;
        if (data) {
          const decoded = jwt_decode(data);
          const userId = decoded.sub;
          console.log('email is :', userId);
          
          axios.get(`/patient/details/${userId}`)
            .then(response => {
              console.log(response.data);
              if(Object.keys(response.data).length === 0){
                axios.get(`/doctor/details/${userId}`)
                .then(response => {
                  console.log('made it here');
                  setUser(response.data);
                  setLoading(false);
                })
                .catch(error => {
                  console.log(error);
                });
              }
              else{
                setUser(response.data);
                setLoading(false);
              }
            })
            .catch(error => {
              console.log(error);
            });
        }
    }, []);

    if (loading) {
      return <p>Loading...</p>
    }

    return (
        <div>
            <AppNavbar />
            <br />
            <div className='profile-container'>
              <div className='profile-header'>
                <h1>Welcome, {user.name} {user.surname}!</h1>
              </div>

              <div className='profile-info'>
                <h2>About Me</h2>
                <br />

                <p>Name: {user.name}</p>
                <p>Surname: {user.surname}</p>
                <p>Email: {user.email}</p>
              </div>
            </div>
        </div>
    );
};

export default Profile;