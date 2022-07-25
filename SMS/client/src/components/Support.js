import React from 'react'
import Footer from './Footer'
import NavBar from './NavBar'
import './Support.css'

function Support() {
  return (
    <div className='support_container'>
        <NavBar/>
        <div className='support_const_info'>
            <h1>Student Service Center Drop-In Hours</h1>
            <table>
                <tbody>
                    <tr>
                        <td>
                            "Monday, Tuesday, Wednesday, Thursday"
                        </td>
                        <td>
                            "9am-5pm"
                        </td>
                    </tr>
                    <tr>
                        <td>
                            "Friday"
                        </td>
                        <td>
                            "10am-5pm"
                        </td>
                    </tr>
                </tbody>
            </table>
            <h1>Phone Hours</h1>
            <table>
                <tbody>
                    <tr>
                        <td>
                            "Monday, Wednesday, Friday"
                        </td>
                        <td>
                            "9:15am-11:55pm; 1:15pm-4:55pm"
                        </td>
                    </tr>
                    <tr>
                        <td>
                            "Tuesday, Thursday"
                        </td>
                        <td>
                            "9am-5pm"
                        </td>
                    </tr>
                </tbody>
            </table>
            <h1>Submit a Ticket</h1>
            <form>
                <input
                    type="email"
                    name="email"
                    placeholder="Your email"
                    className="support-email"
                />
                <input
                    type="text"
                    name="name"
                    placeholder='Your name'
                    className='support_name'
                />
                <input
                    type="text"
                    name="description"
                    placeholder='Description'
                    className='support_description'
                />
                <button>Submit</button>
            </form>
        </div>
        <Footer/>
    </div>
  )
}

export default Support