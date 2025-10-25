import React, { useState } from "react";
import { signup } from "../api";
import { useNavigate } from "react-router-dom";

const SignupPage = () => {
  const [form, setForm] = useState({ username: "", email: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await signup(form);
      alert(res.data);
      navigate("/login");
    } catch (err) {
      alert(err.response?.data || "Error signing up");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Signup</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="username" placeholder="Username" className="form-control mb-2" onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" className="form-control mb-2" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" className="form-control mb-2" onChange={handleChange} required />
        <button className="btn btn-primary">Signup</button>
      </form>
    </div>
  );
};

export default SignupPage;
