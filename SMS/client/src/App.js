import './App.css';
import Login from './components/Login';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Students from './components/creds/Students';
import Support from './components/Support';
import Register from './components/Register';
import Course from './components/Course';
import Registration from './components/Registration';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path='/' exact element={<Login/>}></Route>
          <Route path="/create-account" exact element={<Registration/>}></Route>
          <Route path="/students" exact element={<Students/>}/>
          <Route path="/support" exact element={<Support/>}/>
          <Route path="/register" exact element={<Register/>}/>
          <Route path="/course/:CRN" exact element={<Course/>}/>
        </Routes>
      </Router>
    </>
  );
}

export default App;
