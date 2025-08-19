CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    department VARCHAR(255),
    joining_date DATE NOT NULL,
    annual_leave_balance INT NOT NULL,
    version INT
);

CREATE TABLE leave_request (
    id SERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES employee(id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    days INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    applied_at TIMESTAMP,
    approved_at TIMESTAMP,
    approver VARCHAR(255),
    notes TEXT
);
