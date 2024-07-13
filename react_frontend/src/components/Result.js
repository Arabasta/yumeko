import React from 'react';
import PropTypes from 'prop-types';

const Result = ({ coins }) => (
    <div className="container mt-3">
        <h2>Result</h2>
        <p>Coins: {coins.join(', ')}</p>
    </div>
);

Result.propTypes = {
    coins: PropTypes.arrayOf(PropTypes.number).isRequired,
};

export default Result;
