import React, {Component} from 'react';

class ProjectItem extends Component{
    render(){
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-2">
                            <span className="mx-auto">React</span>
                        </div>
                        <div className="col-lg-6 col-md-4 col-8">
                            <h3>Spring / React Project</h3>
                            <p>Project to create a board with Spring Boot and React</p>
                        </div>
                        <div className="col-md-4 d-lg-block">
                            <ul className="list-group">
                                <a href="#">
                                    <li className="list-group-item">
                                        <i className="fa fa-flag-checkered pr-1">  Project Board
                                        </i>
                                    </li>
                                </a>
                                <a href="#">
                                    <li className="list-group-item">
                                        <i className="fa fa-edit pr-1">  Update Project Info</i>
                                    </li>
                                </a>
                                <a href="#">
                                    <li className="list-group-item">
                                        <i className="fa fa-minus-circle pr-1">  Project Board</i>
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProjectItem; 