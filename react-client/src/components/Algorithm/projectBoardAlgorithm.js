import React from 'react'
import ProjectItem from '../Project/ProjectItem'

export const projectBoardAlgorithm = (projects) => {
    
    if (projects.length <= 0) {
        return(
            <div className="alert alert-info text-center" role="alert">
                No Project on this board
            </div>
        )        
    }
    return(      
        <div>
            {projects.map(project =>(
                <ProjectItem key ={project.id} project={project} /> 
            ))}
        </div>        
    )
}
