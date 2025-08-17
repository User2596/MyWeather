import { useState } from "react";

const Login = () => {
    const [inputs, setInputs] = useState({});
    const [errors, setErrors] = useState({});
    const [showPassword, setShowPassword] = useState(false);

    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setInputs({ ...inputs, [name]: value });
    }

    const validate = () => {
        const newErrors = {};
        const emailRegex = /^[a-zA-Z0-9+.-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if (!inputs.email) {
            newErrors.email = 'Email is required';
        } else if (!emailRegex.test(inputs.email)) {
            newErrors.email = 'Invalid email format';
        }

        if (!inputs.password) {
            newErrors.password = 'Password is required';
        } else if (inputs.password.length < 6) {
            newErrors.password = 'Password must be at least 6 characters';
        }

        return newErrors;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

    }

    return (
        <>
            <form>
                <div>
                    <label htmlFor="email"> Email </label>
                    <input
                        type="email"
                        name="email"
                        id="email"
                        value={inputs.email || ''}
                        onChange={handleChange}
                    />
                </div>
                {errors.email && <div style={{ color: 'red' }}>{errors.email}</div>}
                <div>
                    <label htmlFor="password"> Password </label>
                    <span>
                        <input
                            type={showPassword ? "email" : "password"}
                            name="password"
                            id="password"
                            value={inputs.password || ''}
                            onChange={handleChange}
                        />
                        <input
                            type="checkbox"
                            checked={showPassword}
                            onChange={(e) => setShowPassword(e.target.checked)}
                        />
                    </span>
                </div>
                {errors.password && <div style={{ color: 'red' }}>{errors.password}</div>}
                <button onClick={handleSubmit}>Submit</button>
            </form >
        </>
    )
}

export default Login;