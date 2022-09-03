import React from 'react';
import './App.css';
import Home from '../../pages/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserList from '../Users/UserList';
import UserEdit from '../Users/UserEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/users' exact={true} element={<UserList/>}/>
        <Route path='/users/:id' exact={true} element={<UserEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;