import React from "react";
import NavBar from "./NavBar";
import "./Support.css";

function Support() {
  return (
    <div className="support_container">
      <NavBar />
      <div className="support_const_info">
        <h1>Student Service Center Drop-In Hours</h1>
        <table className="drop-in-table">
          <tbody>
            <tr>
              <td>Monday, Tuesday, Wednesday, Thursday</td>
              <td>9am-5pm</td>
            </tr>
            <tr>
              <td>Friday</td>
              <td>10am-5pm</td>
            </tr>
          </tbody>
        </table>
        <h1>Phone Hours</h1>
        <table className="phone-table">
          <tbody>
            <tr>
              <td>Monday, Wednesday, Friday</td>
              <td>9:15am-11:55pm; 1:15pm-4:55pm</td>
            </tr>
            <tr>
              <td>Tuesday, Thursday</td>
              <td>9am-5pm</td>
            </tr>
          </tbody>
        </table>
        <h1>Submit a Ticket</h1>
        <form>
          <input
            required
            autoComplete="off"
            type="text"
            name="email"
            placeholder="Your email"
            className="support-email"
          />
          <div>
            <input
              required
              autoComplete="off"
              type="text"
              name="name"
              placeholder="Your name"
              className="support_name"
            />
          </div>
          <div className="support-description-container">
            <textarea
              required
              autoComplete="off"
              // type="text"
              name="description"
              placeholder="Description"
              className="support_description"
            />
          </div>
          <button>Submit</button>
        </form>
      </div>
    </div>
  );
}

export default Support;
