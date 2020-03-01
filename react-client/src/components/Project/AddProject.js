import React, { Component } from 'react'
import { Link } from 'react-router-dom';

class AddProject extends Component {
    constructor(){
        super();

        this.state = {
            projectName : "",
            projectIdentifier: "",
            description: "",
            start_date: "",
            end_date: "",
            errors: ""
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    onChange(e){
        this.setState({ [e.target.name]: e.target.value });
    }

    onSubmit(e){        
        e.preventDefault();
        const newProject = {
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date,
            errors: {}
        }

        console.log(newProject);
        
    }

    render() {
        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create / Edit Project Form</h5>
                            <br />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Project Name"
                                        name="projectName"
                                        value={this.state.projectName}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Project Identifier"
                                        name="projectIdentifier"
                                        value={this.state.projectIdentifier}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <textarea
                                        className="form-control"
                                        placeholder="Project Description"
                                        name="description"
                                        value={this.state.description}
                                        onChange={this.onChange}
                                    ></textarea>
                                </div>
                                <h6 className="text-left">Start Date</h6>
                                <div className="form-group">
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="start_date"
                                        value={this.state.start_date}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <h6 className="text-left">Estimated End Date</h6>
                                <div className="form-group">
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="end_date"
                                        value={this.state.end_date}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="submit"
                                        className="btn btn-lg btn-block btn-outline-success mt-4"
                                    />
                                    <Link to="/dashboard" className="btn btn-lg btn-outline-danger btn-block mt-4">Cancel</Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div> 
            </div>
        )
    }
}

export default AddProject
