import React, { useState } from "react";
import { login } from "../api";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [form, setForm] = useState({ username: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await login(form);
      alert(res.data);
      localStorage.setItem("username", form.username);
      navigate("/");
    } catch (err) {
      alert(err.response?.data || "Error logging in");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="username" placeholder="Username" className="form-control mb-2" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" className="form-control mb-2" onChange={handleChange} required />
        <button className="btn btn-primary">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
