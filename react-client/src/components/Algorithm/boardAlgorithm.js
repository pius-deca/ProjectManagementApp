import React from 'react';
import Backlog from "../ProjectBoard/Backlog"

export const boardAlgorithm = (errors, project_tasks) =>{
    if (project_tasks.length < 1) {
        if (errors.projectIdentifier) {
            return(
                <div className="alert alert-danger text-center" role="alert">
                {errors.projectNotFound}
                </div>
            )
        }
        else{
            return(
                <div className="alert alert-info text-center" role="alert">
                    No Project Tasks on this board
                </div>
            )
        }
    }
    return <Backlog project_tasks_prop={project_tasks}/> 
}
