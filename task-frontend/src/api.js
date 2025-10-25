import axios from "axios";

const BASE_URL = "http://localhost:9000/api";

export const signup = (data) => axios.post(`${BASE_URL}/auth/signup`, data);
export const login = (data) => axios.post(`${BASE_URL}/auth/login`, data);

export const getTasks = (username) => axios.get(`${BASE_URL}/tasks`, { params: { username } });
export const addTask = (username, data) => axios.post(`${BASE_URL}/tasks`, data, { params: { username } });
export const updateTask = (username, id, data) => axios.put(`${BASE_URL}/tasks/${id}`, data, { params: { username } });
export const deleteTask = (username, id) => axios.delete(`${BASE_URL}/tasks/${id}`, { params: { username } });
export const markCompleted = (username, id) => axios.put(`${BASE_URL}/tasks/${id}/complete`, null, { params: { username } });
