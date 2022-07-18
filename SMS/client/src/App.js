import './App.css';
import Login from './components/Login';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Students from './components/creds/Students';
import Support from './components/Support';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path='/' exact element={<Login/>}></Route>
          <Route path="/students" exact element={<Students/>}/>
          <Route path="/support" exact element={<Support/>}/>
        </Routes>
      </Router>
    </>
  );
}

export default App;
