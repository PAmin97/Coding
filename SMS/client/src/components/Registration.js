import React from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import axios from "axios";
import "./Registration.css";

function Registration() {
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
    });
  };

  return (
    <div className="account-creation-container">
      <Formik
        initialValues={initialValues}
        onSubmit={handleSubmit}
        validationSchema={validation}
      >
        <Form className="login-form">
          <h1>Create Your Account</h1>
          <label>Username: </label>
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

          <label>Password: </label>
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

          <button type="submit">Create Account</button>
        </Form>
      </Formik>
    </div>
  );
}

export default Registration;
