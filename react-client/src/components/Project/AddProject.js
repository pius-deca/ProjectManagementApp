import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createProject } from '../../actions/projectActions';
import classnames from 'classnames';

class AddProject extends Component {
    constructor(){
        super();

        this.state = {
            projectName : "",
            projectIdentifier: "",
            description: "",
            start_date: "",
            end_date: "",
            errors: {}
        }

        this.onChange = this.onChange.bind(this)
        this.onSubmit = this.onSubmit.bind(this)
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({ errors: nextProps.errors })
        }
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
            startDate: this.state.start_date,
            endDate: this.state.end_date
        }

        this.props.createProject(newProject, this.props.history)
        console.log(newProject);
        
    }

    render() {
        const {errors} = this.state
        return (
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create Project Form</h5>
                            <br />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input
                                        type="text"
                                        className={classnames("form-control", {
                                            "is-invalid":errors.projectName
                                        })}
                                        placeholder="Project Name"
                                        name="projectName"
                                        value={this.state.projectName}
                                        onChange={this.onChange}
                                    />
                                    {errors.projectName && (
                                        <div className="invalid-feedback text-left">{errors.projectName}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                     <input
                                        type="text"
                                        className={classnames("form-control", {
                                            "is-invalid":errors.projectIdentifier
                                        })}
                                        placeholder="Project Identifier"
                                        name="projectIdentifier"
                                        value={this.state.projectIdentifier}
                                        onChange={this.onChange}
                                    />
                                    {errors.projectIdentifier && (
                                        <div className="invalid-feedback text-left">{errors.projectIdentifier}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <textarea
                                        className={classnames("form-control", {
                                            "is-invalid":errors.description
                                        })}
                                        placeholder="Project Description"
                                        name="description"
                                        value={this.state.description}
                                        onChange={this.onChange}
                                    ></textarea>
                                    {errors.description && (
                                        <div className="invalid-feedback text-left">{errors.description}</div>
                                    )}
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

AddProject.propTypes ={
    createProject: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
})
export default connect(
    mapStateToProps,
    {  createProject }
    ) (AddProject);
