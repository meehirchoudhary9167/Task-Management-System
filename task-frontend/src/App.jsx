import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import SignupPage from "./pages/SignupPage";
import LoginPage from "./pages/LoginPage";
import TaskPage from "./pages/TaskPage";
import Navbar from "./pages/Navbar";


const PrivateRoute = ({ children }) => {
  const user = localStorage.getItem("username");
  return user ? children : <Navigate to="/login" />;
};

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/" element={<PrivateRoute><TaskPage /></PrivateRoute>} />
      </Routes>
    </Router>
  );
}

export default App;


