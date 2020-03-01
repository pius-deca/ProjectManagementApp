import React, {Component} from 'react';

class Header extends Component{
    render(){    
        return (
            <nav className="navbar navbar-expand-sm navbar-dark bg-secondary mb-4">
                <div className="container">
                    <a className="navbar-brand" href="Dashbord.html">
                        Project Management Application
                    </a>
                    <button className="naavbar-toggler" type="button" data-toggler="collapse" data-target="#mobile-nav">
                        <span className="navbar-toggler-icon" />
                    </button>

                    <div className="collapse navbar-collapse" id="mobile-nav">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="/dashboard">
                                    Dashbord
                                </a>
                            </li>
                        </ul>
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="register.html">
                                    Sign Up
                                </a>
                            </li>
                        </ul>
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="login.html">
                                    Login
                                </a>
                            </li>
                        </ul>
                    </div>                
                </div>
            </nav>            
        )
    }
}

export default Header; 