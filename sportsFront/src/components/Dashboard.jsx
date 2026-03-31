import { useEffect, useState } from "react";
import axios from "axios";
import EventCard from "./EventCard";

function Dashboard({ user, setUser, setPage }) {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/events")
      .then((res) => setEvents(res.data));
  }, []);

  return (
    <div className="dashboard">

      <div className="header">
        <h1>Welcome {user.username} ({user.role})</h1>

        <div>
          {user.role === "ROLE_ADMIN" && (
            <>
              <button onClick={() => setPage("create")}>
                Create Event
              </button>

              <button onClick={() => setPage("viewBookings")}>
                View Bookings
              </button>
            </>
          )}

          <button onClick={() => {
            setUser(null);
            setPage("login"); // ✅ reset page
        }}>
        Logout
        </button>
        </div>
      </div>

      <h2>Available Events</h2>

      <div className="event-grid">
        {events.map((event) => (
          <EventCard
            key={event.id}
            event={event}
            user={user}
            setPage={setPage}
          />
        ))}
      </div>
    </div>
  );
}

export default Dashboard;