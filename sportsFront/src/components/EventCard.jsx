import axios from "axios";

function EventCard({ event, user, setPage }) {

  // ✅ Check if event is in past
  const today = new Date();
  const eventDate = new Date(event.eventDate);

  // normalize time (avoid time issues)
  today.setHours(0, 0, 0, 0);
  eventDate.setHours(0, 0, 0, 0);

  const isPast = eventDate < today;

  const bookEvent = async () => {
    try {
      const res = await axios.post(
        `http://localhost:8081/api/events/${event.id}/book/${user.id}`
      );

      alert(res.data); // ✅ show backend message

      // ✅ only go to success page if success
      if (res.data.includes("success")) {
        setPage("success");
      }

    } catch (err) {
      console.error(err);
      alert("Booking Failed ❌");
    }
  };

  return (
    <div className="card">
      <h3>{event.title}</h3>

      <p>{event.description}</p>
      <p>📍 {event.venue}</p>

      {/* ✅ formatted date */}
      <p>📅 {new Date(event.eventDate).toLocaleDateString()}</p>

      <p>Seats: {event.availableSeats}</p>

      {/* ✅ Only students can book */}
      {user.role === "ROLE_STUDENT" && (
        <button
          disabled={event.availableSeats === 0 || isPast}
          onClick={bookEvent}
        >
          {isPast
            ? "Event Over"
            : event.availableSeats === 0
            ? "Full"
            : "Book Now"}
        </button>
      )}
    </div>
  );
}

export default EventCard;