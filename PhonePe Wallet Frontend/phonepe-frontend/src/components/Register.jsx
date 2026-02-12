import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "./Auth.css";

function Register() {
    const [form, setForm] = useState({
        name: "",
        phoneNumber: "",
        upiId: "",
        pin: ""
    });

    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            const payload = {
                name: form.name,
                phoneNumber: form.phoneNumber,
                upiId: form.upiId,
                pin: form.pin
            };

            await api.post("/users/register", payload);
            alert("Registration successful");
            navigate("/login");
        } catch (err) {
            console.error("Backend error:", err.response?.data);
            alert(
                err.response?.data?.message ||
                "Registration failed"
            );
        }
    };

    return (
        <div className="card">
            <h2>Register</h2>

            <input
                placeholder="Full Name"
                value={form.name}
                onChange={e => setForm({ ...form, name: e.target.value })}
            />

            <input
                placeholder="Phone Number"
                value={form.phoneNumber}
                onChange={e => setForm({ ...form, phoneNumber: e.target.value })}
            />

            <input
                placeholder="UPI ID (e.g. name@upi)"
                value={form.upiId}
                onChange={e => setForm({ ...form, upiId: e.target.value })}
            />

            <input
                type="password"
                placeholder="4-digit PIN"
                value={form.pin}
                onChange={e => setForm({ ...form, pin: e.target.value })}
            />

            <button onClick={handleRegister}>Register</button>

            <p onClick={() => navigate("/login")}>
                Already have an account? Login
            </p>
        </div>
    );
}

export default Register;
