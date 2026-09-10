import { useEffect, useState } from "react";
import axios from "axios";

function ViewBookings({ user, setPage }) {

  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    axios
      .get(`https://ttdeployment-s3co.onrender.com/api/events/admin/bookings/${user.id}`)
      .then((res) => setBookings(res.data))
      .catch(() => alert("Error fetching bookings"));
  }, []);

  return (
    <div className="dashboard">

      <h2>All Student Registrations</h2>

      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>Student</th>
            <th>Event</th>
          </tr>
        </thead>

        <tbody>
          {bookings.map((b) => (
            <tr key={b.id}>
              <td>{b.user.username}</td>
              <td>{b.event.title}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button onClick={() => setPage("dashboard")}>
        Back
      </button>
    </div>
  );
}

export default ViewBookings;
