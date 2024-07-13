import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import Form from './components/Form';
import Result from './components/Result';
import ErrorMessage from './components/ErrorMessage';
import Footer from "./components/footer";
import handleError from './utils/handleHttpError';

function App() {
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (data) => {
    setError(null);
    setResult(null);

    try {
      const response = await axios.post(`${process.env.REACT_APP_API_URL}/api/v1/coin-change`, data);
      setResult(response.data.coins);
    } catch (err) {
        setError(handleError(err));
    }
  };

  return (
      <div className="App container">
        <h1 className="mt-5">Coin Change Kei Yam</h1>
        <Form onSubmit={handleSubmit} />
        {result && <Result coins={result} />}
        {error && <ErrorMessage message={error} />}
        <Footer />
      </div>
  );
}

export default App;
