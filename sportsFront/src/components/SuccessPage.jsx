import Confetti from "react-confetti";

function SuccessPage({ setUser, setPage }) {
  return (
    <div className="success-container">
      <Confetti />

      <h1>🎉 Ticket Booked Successfully!</h1>

      <button onClick={() => setPage("dashboard")}>
        Back
      </button>

      <button onClick={() => {
        setUser(null);
        setPage("login"); // ✅ reset page
        }}>
        Logout
        </button>
    </div>
  );
}

export default SuccessPage;