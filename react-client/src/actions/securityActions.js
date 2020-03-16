import axios from 'axios'
import { GET_ERRORS, SET_CURRENT_USER } from './types'
import setToken from '../securityUtils/setToken'
import jwt_decode from 'jwt-decode'

export const createNewUser = (newUser, history) => async dispatch =>{
    try {
        await axios.post("/api/users/register", newUser)
        history.push("/login")
        dispatch({
            type:GET_ERRORS,
            payload:{}
        })
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
        
    }
}

export const loginUser = LoginRequest => async dispatch=>{
    try {
        const response = await axios.post("/api/users/login", LoginRequest);
        const {token} = response.data;
        localStorage.setItem("token", token)
        setToken(token)
        const decodedToken = jwt_decode(token)

        dispatch({
            type:SET_CURRENT_USER,
            payload:decodedToken
        })        
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
}

export const logout = () => dispatch=>{
    localStorage.removeItem("token")
    setToken(false)
    dispatch({
        type:SET_CURRENT_USER,
        payload:{}
    })
}