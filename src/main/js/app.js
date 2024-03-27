'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom'; // Import pro Link z React Routeru

export function App() {
    return (
        <div>
            <h1>Vítejte v aplikaci Kalendář</h1>
            <nav>
                <ul>
                    <li>
                        <Link to="/calendar">Kalendář</Link>
                    </li>
                    <li>
                        <Link to="/profile">Můj profil</Link>
                    </li>
                    <li>
                        <Link to="/logout">Odhlásit se</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);
