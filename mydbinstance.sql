
CREATE TABLE employee (
    employeeid INTEGER PRIMARY KEY,
    username VARCHAR2(20) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    ismanager VARCHAR(1) NOT NULL
);

CREATE TABLE reimbursement (
    reimbursementid INTEGER PRIMARY KEY,
    employeeid INTEGER,
    expense NUMBER(9,2) NOT NULL CHECK(expense > 0),
    status VARCHAR2(10) NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employeeid) REFERENCES employee(employeeid)
);

CREATE SEQUENCE employee_seq
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE reimbursement_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER tr_insert_employee
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT employee_seq.NEXTVAL INTO :NEW.employeeid FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER tr_insert_reimbursement
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    SELECT reimbursement_seq.NEXTVAL INTO :NEW.reimbursementid FROM DUAL;
END;
/