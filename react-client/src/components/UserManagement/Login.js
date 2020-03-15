import React, { Component } from 'react'
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import { loginUser} from '../../actions/securityActions'
import classnames from 'classnames'

class Login extends Component {
    constructor(){
        super()

        this.state ={
            "username":"",
            "password": "",
            errors:{}
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
        this.setState({[e.target.name]: e.target.value})
    }

    onSubmit(e){
        e.preventDefault()
        const login ={
            username:this.state.username,
            password:this.state.password
        }

        this.props.loginUser(login, this.props.history)
    }    

    render() {
        const{ errors } = this.state
        return (
            <div className="login">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Log In</h1>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="email" 
                                        className={classnames ("form-control form-control-lg", {
                                            "is-invalid": errors.username
                                        })} 
                                        placeholder="Email Address" 
                                        name="username"
                                        // value={this.state.username}
                                        onChange={this.onChange}
                                    />
                                    {errors.username && (
                                        <div className="invalid-feedback text-left">{errors.username}</div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="password" 
                                        className={classnames ("form-control form-control-lg", {
                                            "is-invalid": errors.password
                                        })}
                                        placeholder="Password" 
                                        name="password"                                        
                                        // value={this.state.username}
                                        onChange={this.onChange}
                                    />
                                    {errors.password && (
                                        <div className="invalid-feedback text-left">{errors.password}</div>
                                    )}
                                </div>
                                <input type="submit" className="btn btn-outline-info btn-lg btn-block mt-4"/>
                            </form>
                        </div>
                    </div>
                </div>                
            </div>
        )
    }
}

Login.propTypes ={
    loginUser:PropTypes.func.isRequired,
    errors:PropTypes.object.isRequired
}

const mapStateToProps = state =>({
    errors:state.errors
})
export default connect(
    mapStateToProps, 
    {loginUser}
    )(Login) 
