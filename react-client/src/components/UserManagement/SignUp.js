import React, { Component } from 'react'

class SignUp extends Component {
    render() {
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create Your Account</p>
                            <form>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" placeholder="Name" name="name"/>
                                </div>
                                <div className="form-group">
                                    <input type="email" className="form-control form-control-lg" placeholder="Email Address" name="email"/>
                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control form-control-lg" placeholder="Password" name="password"/>
                                </div>
                                <div className="form-group">
                                    <input type="password" className="form-control form-control-lg" placeholder="Confirm Password" name="confirmPassword"/>
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

export default SignUp
