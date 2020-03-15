import React from 'react';
import './App.css';
import DashBoard from './components/Dashboard';
import Header from './components/Layout/Header'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import AddProject from './components/Project/AddProject';
import { Provider } from 'react-redux';
import store from './store';
import UpdateProject from './components/Project/UpdateProject';
import ProjectBoard from './components/ProjectBoard/ProjectBoard';
import AddProjectTask from './components/ProjectBoard/ProjectTask/AddProjectTask';
import UpdateProjectTask from './components/ProjectBoard/ProjectTask/UpdateProjectTask';
import Landing from './components/Layout/Landing'
import SignUp from './components/UserManagement/SignUp';
import Login from './components/UserManagement/Login';
import jwt_decode from 'jwt-decode'
import setToken from './securityUtils/setToken';
import { SET_CURRENT_USER } from './actions/types';

const token = localStorage.getItem("token")
if (token) {
  setToken(token)
  const decodedToken = jwt_decode(token)

  store.dispatch({
    type:SET_CURRENT_USER,
    payload:decodedToken
  })

  const currentTime =  Date.now()/100
  if (decodedToken.exp < currentTime) {
    window.location.href = "/"
  }

}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          {
            // Public Routes
          }
          <Route exact path="/" component={Landing} />
          <Route exact path="/register" component={SignUp} />
          <Route exact path="/login" component={Login} />

          {
            // Private Routes
          }
          <Route exact path="/dashboard" component={DashBoard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjectTask/:id" component={AddProjectTask} />
          <Route exact path="/updateProjectTask/:backlog_id/:pt_sequence" component={UpdateProjectTask} />
        </div>
      </Router>
    </Provider>
  );
} 
 
export default App;
