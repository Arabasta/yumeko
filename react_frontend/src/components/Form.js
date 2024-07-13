import React, { useState } from 'react';
import PropTypes from 'prop-types';
import 'bootstrap/dist/css/bootstrap.min.css';

const VALID_DENOMINATIONS = [0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000];

const Form = ({ onSubmit }) => {
    const [amount, setAmount] = useState('');
    const [selectedDenominations, setSelectedDenominations] = useState([]);
    const [validationError, setValidationError] = useState('');

    const handleDenominationClick = (denomination) => {
        setSelectedDenominations((prev) => {
            if (prev.includes(denomination)) {
                return prev.filter((d) => d !== denomination);
            } else {
                return [...prev, denomination];
            }
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setValidationError('');

        if (selectedDenominations.length === 0) {
            setValidationError('Please select at least one denomination.');
            return;
        }

        onSubmit({ amount: parseFloat(amount), denominations: selectedDenominations });
    };

    return (
        <div className="container">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Amount:</label>
                    <input
                        type="number"
                        className="form-control"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Denominations:</label>
                    <div>
                        {VALID_DENOMINATIONS.map((denomination) => (
                            <button
                                key={denomination}
                                type="button"
                                className={`btn btn-outline-primary m-1 ${selectedDenominations.includes(denomination) ? 'active' : ''}`}
                                onClick={() => handleDenominationClick(denomination)}
                            >
                                {denomination}
                            </button>
                        ))}
                    </div>
                </div>
                <button type="submit" className="btn btn-primary">Calculate</button>
            </form>
            {validationError && <p className="text-danger">{validationError}</p>}
        </div>
    );
};

Form.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default Form;
