import React from 'react';
import '../../App.css'
import CardItem from '../CardItem'
import Footer from '../Footer';
import NavBar from '../NavBar';
import './Cards.css'

export default function Students() {
    return (
        <div className='cards'>
            <NavBar/>
            <img className='student-image' src='/images/SMS-Students.jpg' alt='Penn Campus'/>
            <h1 className='student-title'>Student Page</h1>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem
                        text='Registration'
                        path='/register'
                        src='/images/SMS-ViewCourses.jpg'
                        description='Add/Drop/View your courses'
                        />

                        <CardItem 
                        text='My Profile'
                        path='/my-profile'
                        src='/images/SMS-Profile.jpg'
                        description='Update your contact information, submit your medical files'
                        />

                        <CardItem 
                        text='Support'
                        path='/support'
                        src='/images/SMS-Support.jpg'
                        description='Give us a call or send us an email, if you need any help'
                        />
                    </ul>
                </div>
            </div>
            <Footer/>
        </div>
    )
}