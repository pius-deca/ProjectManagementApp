import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions"
import { projectTaskBoardAlgorithm } from '../Algorithm/projectTasksBoardAlgorithm';

class ProjectBoard extends Component {
    constructor(){
        super();
        this.state = {
            errors:{}
        }
    }
    
    componentDidMount(){
        const { id } = this.props.match.params;
        this.props.getBacklog(id)
    }

    componentWillReceiveProps(nextProps){
        if (nextProps.errors) {
            this.setState({
                errors: nextProps.errors
            })
        }
    }

    render() {
        const { id } = this.props.match.params;
        const { project_tasks } = this.props.backlog; 
        const { errors } = this.state

        let boardContent;
        boardContent = projectTaskBoardAlgorithm(errors, project_tasks)

        return (
            <div className="container">
                <Link to={`/addProjectTask/${id}`} className="btn btn-outline-info mb-3">
                    <i className="fa fa-plus-circle"> Create Project Task</i>
                </Link>
                <br/>   
                {boardContent}         
            </div>
        )
    }
}

ProjectBoard.propTypes ={
    backlog:PropTypes.object.isRequired,
    getBacklog:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired
}

const mapStateToProps = state =>({
    backlog:state.backlog,
    errors:state.errors
})


export default connect(
    mapStateToProps, 
    {getBacklog}
    )(ProjectBoard);
