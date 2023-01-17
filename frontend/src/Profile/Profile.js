import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AppNavbar from '../AppNavbar.js';
import jwt_decode from 'jwt-decode';

const Profile = () => {
    const [user, setUser] = useState({});
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const jwt = localStorage.getItem('jwt');
        if (jwt) {
          const decoded = jwt_decode(jwt);
          const userId = decoded.sub;
          axios.get(`/patient/${userId}`)
            .then(response => {
              setUser(response.data);
              setLoading(false);
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
            <h2>Welcome To Your Profile</h2>

            <p>Name: {user.name}</p>
            <p>Surname: {user.surname}</p>
            <p>Email: {user.email}</p>
        </div>
    );
};

export default Profile;