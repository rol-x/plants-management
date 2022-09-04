import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../App/AppNavbar';

const UserEdit = () => {
  const initialFormState = {
    firstName: '',
    lastName: '',
    address: {
      street: '',
      city: '',
      postalCode: ''
    }
  };

  const [user, setUser] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      fetch(`/api/users/${id}`)
        .then(response => response.json())
        .then(data => setUser(data));
    }
  }, [id, setUser]);

  const handleChange = (event) => {
    const { name, value } = event.target

    setUser({ ...user, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch('/api/users' + (user.id ? '/' + user.id : ''), {
      method: (user.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    });
    setUser(initialFormState);
    navigate('/users');
  }

  const title = <h2>{user.id ? 'Edit User' : 'Add User'}</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
        <FormGroup>
            <Label for="firstName">First name</Label>
            <Input type="text" name="firstName" id="firstName" value={user.firstName || ''}
                   onChange={handleChange} autoComplete="firstName"/>
          </FormGroup>
          <FormGroup>
            <Label for="lastName">Last name</Label>
            <Input type="text" name="lastName" id="lastName" value={user.lastName || ''}
                   onChange={handleChange} autoComplete="lastName"/>
          </FormGroup>
          <div className="row">
            {/* TODO: This is not working! */}
            <FormGroup className="col-md-4 mb-3">
              <Label for="stateOrProvince">Street</Label>
              <Input type="text" name="street" id="street" value={user.address.street || ''}
                     onChange={handleChange} autoComplete="street"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="country">City</Label>
              <Input type="text" name="city" id="city" value={user.address.city || ''}
                     onChange={handleChange} autoComplete="city"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="country">Postal Code</Label>
              <Input type="text" name="postalCode" id="postalCode" value={user.address.postalCode || ''}
                     onChange={handleChange} autoComplete="postalCode"/>
            </FormGroup>
          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/users">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default UserEdit;