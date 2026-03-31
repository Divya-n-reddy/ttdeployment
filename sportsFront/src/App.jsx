import { useState } from "react";
import AuthPage from "./components/AuthPage";
import Dashboard from "./components/Dashboard";
import SuccessPage from "./components/SuccessPage";
import CreateEvent from "./components/CreateEvent";
import ViewBookings from "./components/ViewBookings";

function App() {
  const [user, setUser] = useState(null);
  const [page, setPage] = useState("login");

  if (!user) {
  return <AuthPage setUser={(u) => {
    setUser(u);
    setPage("dashboard"); // ✅ always go to dashboard after login
  }} />;
}

  if (page === "success") {
    return <SuccessPage setUser={setUser} setPage={setPage} />;
  }

  if (page === "create") {
    return <CreateEvent user={user} setPage={setPage} />;
  }

  if (page === "viewBookings") {
    return <ViewBookings user={user} setPage={setPage} />;
  }

  return <Dashboard user={user} setUser={setUser} setPage={setPage} />;
}

export default App;