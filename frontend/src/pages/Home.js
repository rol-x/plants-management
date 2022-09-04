import React from 'react';
import './Home.css';
import AppNavbar from '../components/App/AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

const Home = () =>
  (
    <div>
      <AppNavbar />
      <Container fluid>
      <div className="row">
        <div className="col-md-5 pt-5" />
        <Button className="col-md-2 pt-5" color="link"><Link to="/waterings">Plant waterings</Link></Button>
      </div>
      <div className="row">
        <div className="col-md-4 pt-5" />
        <Button className="col-md-2 pt-5 mt-3" color="link"><Link to="/users">Manage Users</Link></Button>
        <Button className="col-md-2 pt-5 mt-3" color="link"><Link to="/plants">Manage Plants</Link></Button>
      </div>
      </Container>
    </div>
  );

export default Home;