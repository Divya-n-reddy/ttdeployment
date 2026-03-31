import { useState } from "react";
import axios from "axios";

function AuthPage({ setUser }) {
  const [isLogin, setIsLogin] = useState(true);
  const [adminSecret, setAdminSecret] = useState("");

  const [form, setForm] = useState({
    username: "",
    email: "",
    password: ""
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (isLogin) {
        const res = await axios.post(
          "http://localhost:8081/api/users/login",
          {
            username: form.username,
            password: form.password
          }
        );

        alert(`Welcome ${res.data.role}`);
        setUser(res.data);
      } else {
        let url = "http://localhost:8081/api/users/register";

        if (adminSecret) {
          url += `?adminSecret=${adminSecret}`;
        }

        await axios.post(url, form);

        alert("Registered Successfully!");
        setIsLogin(true);
      }
    } catch (err) {
      alert("Invalid credentials ❌");
    }
  };

  return (
    <div className="auth-container">
      <h2>{isLogin ? "Login" : "Register"}</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="username"
          placeholder="Username"
          onChange={handleChange}
          required
        />

        {!isLogin && (
          <>
            <input
              name="email"
              placeholder="Email"
              onChange={handleChange}
              required
            />

            <input
              placeholder="Admin Secret (optional)"
              onChange={(e) => setAdminSecret(e.target.value)}
            />
          </>
        )}

        <input
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
          required
        />

        <button>{isLogin ? "Login" : "Register"}</button>
      </form>

      <p onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? "Create Account" : "Already have account?"}
      </p>
    </div>
  );
}

export default AuthPage;