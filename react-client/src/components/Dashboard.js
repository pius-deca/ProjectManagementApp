import React, {Component} from 'react';
import ProjectItem from './Project/ProjectItem';

class DashBoard extends Component{
    render(){    
        return (
            <nav className="navabar navbar-expand-sm navbar-dark bg-primary mb-4">
                <h1 className="alert alert-warning">
                    Welcome
                </h1>
                <ProjectItem />
            </nav>
            
        )
    }
}

export default DashBoard; 