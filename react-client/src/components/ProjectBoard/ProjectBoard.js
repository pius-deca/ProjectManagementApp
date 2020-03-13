import React, { Component } from 'react'
import { Link } from 'react-router-dom';

class ProjectBoard extends Component {
    render() {
        const { id } = this.props.match.params;
        return (
            <div className="container">
                <Link to={`/addProjectTask/${id}`} className="btn btn-outline-info mb-3">
                    <i className="fa fa-plus-circle"> Create Project Task</i>
                </Link>
                <br/>
                <div className="container">
                    <div className="row">
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-secondary text-white">
                                    <h3>TODO</h3>
                                </div>
                                <div className="card mb-1 bg-light">
                                    <div className="card-header text-primary">
                                        ID: projectSequence -- Priority: priorityString
                                    </div>
                                    <div className="card-body bg-light">
                                        <h5 className="card-title">
                                            project_task.summary
                                        </h5>
                                        <p className="card-text text-truncate">
                                            project_task.acceptanceCriteria
                                        </p>
                                        <a href="#" className="btn btn-outline-primary">View / Update</a>

                                        <button className="btn btn-outline-danger">Delete</button>
                                    </div>
                                </div>                                
                            </div>                            
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-primary text-white">
                                    <h3>In Progress</h3>
                                </div>
                                                   
                            </div>                            
                        </div>
                        <div className="col-md-4">
                            <div className="card text-center mb-2">
                                <div className="card-header bg-success text-white">
                                    <h3>Done</h3>
                                </div>                               
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProjectBoard;
