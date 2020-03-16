import React, {Component} from 'react';
import CreateProjectButton from './Project/CreateProjectButton';
import { connect } from 'react-redux';
import { getProjects } from '../actions/projectActions';
import PropTypes from 'prop-types';
import { projectBoardAlgorithm } from './Algorithm/projectBoardAlgorithm';

class DashBoard extends Component{

    componentDidMount(){
        this.props.getProjects();
    }

    render(){ 
        const { projects } = this.props.project   

        let boardContent;
        boardContent = projectBoardAlgorithm(projects);
        
        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Projects</h1>
                            <br />
                            <CreateProjectButton />
                            <br />
                            <br /> 
                            
                            { boardContent }
                        </div>
                    </div>
                </div>
            </div>
            
        )
    }
}
DashBoard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
}

const mapStateToProps = state =>({
    project: state.project
})
export default connect(
    mapStateToProps,
    {getProjects}
    ) (DashBoard); 