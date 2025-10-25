import React, { useEffect, useState } from "react";
import { getTasks, addTask, updateTask, deleteTask, markCompleted } from "../api";

const TaskPage = () => {
  const username = localStorage.getItem("username");
  const [tasks, setTasks] = useState([]);
  const [form, setForm] = useState({ title: "", description: "", dueDate: "" });
  const [editId, setEditId] = useState(null);

  // Fetch tasks and convert completed field to boolean
  const fetchTasks = async () => {
    try {
      const res = await getTasks(username);
      const tasksWithBool = res.data.map(task => ({
        ...task,
        completed: Boolean(task.completed),  // convert 0/1 to true/false
        dueDate: task.dueDate ? task.dueDate : null
      }));
      setTasks(tasksWithBool);
    } catch (err) {
      console.error("Error fetching tasks:", err);
    }
  };

  useEffect(() => { fetchTasks(); }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editId) {
        await updateTask(username, editId, { ...form, completed: false });
        setEditId(null);
      } else {
        await addTask(username, { ...form, completed: false });
      }
      setForm({ title: "", description: "", dueDate: "" });
      fetchTasks();
    } catch (err) {
      console.error("Error saving task:", err);
    }
  };

  const handleEdit = (task) => {
    setForm({
      title: task.title,
      description: task.description,
      dueDate: task.dueDate ? task.dueDate.slice(0, 16) : ""  // slice to remove seconds/microseconds
    });
    setEditId(task.id);
  };

  const handleDelete = async (id) => {
    try {
      await deleteTask(username, id);
      fetchTasks();
    } catch (err) {
      console.error("Error deleting task:", err);
    }
  };

  const handleComplete = async (id) => {
    try {
      await markCompleted(username, id);
      fetchTasks();
    } catch (err) {
      console.error("Error marking completed:", err);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("username");
    window.location.reload();
  };

  return (
    <div className="container mt-5">
      <h2>Task Manager</h2>
      <button className="btn btn-danger mb-3" onClick={handleLogout}>Logout</button>

      {/* Task Form */}
      <form onSubmit={handleSubmit} className="mb-3">
        <input
          type="text"
          name="title"
          placeholder="Title"
          className="form-control mb-2"
          value={form.title}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="description"
          placeholder="Description"
          className="form-control mb-2"
          value={form.description}
          onChange={handleChange}
        />
        <input
          type="datetime-local"
          name="dueDate"
          className="form-control mb-2"
          value={form.dueDate}
          onChange={handleChange}
        />
        <button className="btn btn-primary">{editId ? "Update Task" : "Add Task"}</button>
      </form>

      {/* Task Table */}
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {tasks.length === 0 && (
            <tr>
              <td colSpan="5" className="text-center">No tasks found.</td>
            </tr>
          )}
          {tasks.map(task => (
            <tr key={task.id} className={task.completed ? "table-success" : ""}>
              <td>{task.title}</td>
              <td>{task.description}</td>
              <td>{task.dueDate ? new Date(task.dueDate).toLocaleString() : "-"}</td>
              <td>{task.completed ? "Completed" : "Pending"}</td>
              <td>
                {!task.completed && (
                  <button className="btn btn-success btn-sm me-2" onClick={() => handleComplete(task.id)}>Complete</button>
                )}
                <button className="btn btn-warning btn-sm me-2" onClick={() => handleEdit(task)}>Edit</button>
                <button className="btn btn-danger btn-sm" onClick={() => handleDelete(task.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TaskPage;
