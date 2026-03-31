import { useState } from "react";
import axios from "axios";

function CreateEvent({ user, setPage }) {

  const [form, setForm] = useState({
    title: "",
    description: "",
    venue: "",
    eventDate: "",
    totalSeats: 0
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const createEvent = async (e) => {
    e.preventDefault();

    try {
      // ✅ Ensure correct payload
      const payload = {
        ...form,
        eventDate: form.eventDate // must be YYYY-MM-DD
      };

      console.log("Sending data:", payload); // 🔍 Debug

      await axios.post(
        `http://localhost:8081/api/events/create/${user.id}`,
        payload
      );

      alert("Event Created Successfully ✅");
      setPage("dashboard");

    } catch (err) {
      console.error("Error:", err.response?.data || err.message); // 🔍 Debug
      alert("Error creating event ❌");
    }
  };

  return (
    <div className="auth-container">
      <h2>Create Event</h2>

      <form onSubmit={createEvent}>
        
        <input
          name="title"
          placeholder="Title"
          onChange={handleChange}
          required
        />

        <input
          name="description"
          placeholder="Description"
          onChange={handleChange}
          required
        />

        <input
          name="venue"
          placeholder="Venue"
          onChange={handleChange}
          required
        />

        {/* ✅ DATE INPUT (IMPORTANT) */}
        <input
          type="date"
          name="eventDate"
          value={form.eventDate}
          onChange={handleChange}
          required
        />

        <input
          name="totalSeats"
          type="number"
          placeholder="Seats"
          onChange={handleChange}
          required
        />

        <button>Create</button>
      </form>

      <button onClick={() => setPage("dashboard")}>
        Back
      </button>
    </div>
  );
}

export default CreateEvent;