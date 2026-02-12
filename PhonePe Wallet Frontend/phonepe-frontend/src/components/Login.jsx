import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "./Auth.css";

function Login() {
    const [phone, setPhone] = useState("");
    const [pin, setPin] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const payload = {
                phoneNumber: phone,
                pin: pin
            };

            const res = await api.post("/users/login", payload);

            localStorage.setItem("upiId", res.data.data.upiId);
            localStorage.setItem("name", res.data.data.name);

            navigate("/dashboard");
        } catch (err) {
            console.error(err.response?.data);
            alert("Invalid credentials");
        }
    };


    return (
        <div className="card">
            <h2 style={{color:"orange"}}>MRU Pay</h2>
            <h2>Login</h2>

            <input
                placeholder="Phone Number"
                onChange={e => setPhone(e.target.value)}
            />

            <input
                type="password"
                placeholder="PIN"
                onChange={e => setPin(e.target.value)}
            />

            <button onClick={handleLogin}>Login</button>

            <p onClick={() => navigate("/register")}>
                Register
            </p>
        </div>
    );
}

export default Login;
