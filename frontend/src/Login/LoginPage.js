import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './LoginPage.css';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [token, setToken] = useState(null);
    const history = useHistory();

    function handleSubmit(event) {
        event.preventDefault();
        axios.post('/login',{ email, password })
            .then(response => {
                //console.log(response.json());
                console.log(response.data.accessToken);

                const accessToken = response.data.accessToken;

                localStorage.setItem('token', accessToken);

                setToken(accessToken);
                // localStorage.setItem('isLoggedIn', JSON.stringify(true));
                history.push('/');
                history.go('/');
            })
            .catch(error => {
                setIsLoading(false);
                setError(error.response.data);
            });
    }

    return (
        <form onSubmit={handleSubmit}>
            {error && <div className="error-message">{error}</div>}
            <input type="email" value={email} onChange={event => setEmail(event.target.value)} placeholder="Email" />
            <input type="password" value={password} onChange={event => setPassword(event.target.value)} placeholder="Password" />
            <button type="submit" disabled={isLoading}>
                {isLoading ? 'Loading...' : 'Log In'}
            </button>
        </form>
    );
}

export default LoginPage;
