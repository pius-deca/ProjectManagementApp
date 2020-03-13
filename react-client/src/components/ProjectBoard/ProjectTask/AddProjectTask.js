import React, { Component } from 'react'
import { Link } from 'react-router-dom';

class AddProjectTask extends Component {
    render() {
        const { id } = this.props.match.params;
        return (
            <div className="add-projectTask">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/projectBoard/${id}`} className="btn btn-light">Back to project Board</Link>
                            <h6 className="display-4 text-center">Add / Update Project Task</h6>
                            <p className="lead text-center">Project Name + Project Code</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" name="summary" placeholder="Project Task summary" />                                    
                                </div>
                                <div className="form-group">
                                    <textarea className="form-control form-control-lg" name="acceptanceCriteria" placeholder="Acceptance Criteria" />                                    
                                </div>
                                <h6 className="text-left">Due Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="dueDate"/>
                                </div>
                                <div className="form-group">
                                    <select className="form-control form-control-lg" name="priority">
                                        <option value={0}>Select Priority</option>
                                        <option value={1}>High</option>
                                        <option value={2}>Medium</option>
                                        <option value={3}>Low</option>
                                    </select>
                                </div>
                                <div className="form-group">
                                    <select className="form-control form-control-lg" name="status">
                                        <option value="">Select status</option>
                                        <option value="TO_DO">TODO</option>
                                        <option value="IN_PROGRESS">IN PROGRESS</option>
                                        <option value="DONE">DONE</option>
                                    </select>
                                </div>
                                <input type="submit" className="btn btn-outline-secondary btn-lg btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default AddProjectTask;  
