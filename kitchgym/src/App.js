import React from 'react';
import logo from './logo.svg';
import './App.css';
import { 
  makeStyles,
  Button, 
  TextField 
} from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },
}));

function App() {
  const {classes} = useStyles();
  return (
    <div className="App">
      <h1 className="App-header">
        Welcome to KitchGym!
      </h1>
      <h2 className="App-header">
        Here to assist you along your health and fitness journey.
      </h2>
      <TextField
        id="standard-name"
        label="Name"
        className="inputField"
        margin="normal"
      />
    </div>
  );
}

export default App;
