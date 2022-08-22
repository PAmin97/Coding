import React from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import axios from "axios";
import "./Registration.css";
import { useNavigate } from "react-router-dom";

function Registration() {
  let navigate = useNavigate();

  const initialValues = {
    username: "",
    password: "",
  };

  const validation = Yup.object().shape({
    username: Yup.string().min(3).max(15).required(),
    password: Yup.string().min(4).max(20).required(),
  });

  const handleSubmit = (data) => {
    axios.post("http://localhost:3001/auth", data).then(() => {
      console.log(data);
      navigate("/login");
    });
  };

  return (
    <div className="Create-Account">
      <img src="/images/SMS-Students.jpg" alt="Penn Campus"/>
      <Formik
        initialValues={initialValues}
        onSubmit={handleSubmit}
        validationSchema={validation}
      >
        <Form className="login-form">
          <h2>Create Your Account</h2>
          <label className="account-label">Username: </label>
          <ErrorMessage
            className="errormessage"
            name="username"
            component="span"
          />
          <Field
            className="input-container"
            autoComplete="off"
            id="input-username"
            name="username"
            placeholder="(Ex. student1...)"
          />

          <label className="account-label">Password: </label>
          <ErrorMessage
            className="errormessage"
            name="password"
            component="span"
          />
          <Field
            className="input-container"
            autoComplete="off"
            type="password"
            id="input-passwordß"
            name="password"
            placeholder="(Ex. pass123...)"
          />

          <button className="account-btn" type="submit">Create Account</button>
        </Form>
      </Formik>
    </div>
  );
}

export default Registration;
