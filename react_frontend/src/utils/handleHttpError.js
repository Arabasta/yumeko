const handleError = (err) => {
    if (err.response) {
        if (err.response.status === 400) {
            if (typeof err.response.data === 'string') {
                return err.response.data || 'Invalid input data. Please check your values.';
            } else if (typeof err.response.data === 'object') {
                return Object.values(err.response.data).join(', ') || 'Invalid input data. Please check your values.';
            } else {
                return 'Invalid input data. Please check your values.';
            }
        } else {
            return 'An error occurred on the server.';
        }
    } else {
        return 'Failed to connect to the server.';
    }
};

export default handleError;
