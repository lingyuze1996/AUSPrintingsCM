DROP TABLE APPUSER;
CREATE TABLE APPUSER (
    uId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    uRole VARCHAR(10) NOT NULL,
    uFirstName VARCHAR(20) NOT NULL,
    uLastName VARCHAR(20) NOT NULL,
    uDOB CHAR(10) NOT NULL,
    uGender CHAR(1) NOT NULL,
    uEmail VARCHAR(40) NOT NULL,
    uPassword CHAR(64) NOT NULL    
);

ALTER TABLE APPUSER
    ADD CONSTRAINT chk_gender CHECK (uGender IN (
        'F',
        'M'
    ));
ALTER TABLE APPUSER ADD CONSTRAINT appuser_pk PRIMARY KEY ( uId );
ALTER TABLE APPUSER ADD CONSTRAINT email_unique UNIQUE (uEmail);

INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('admin', 'Yuze', 'Ling', '1996-06-13', 'M', 'ylin0081@student.monash.edu', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('normal', 'John', 'Smith', '1993-05-10', 'F', 'johnsmith@gmail.com', '317b32c143692b9939c197f6a5df54f9698df9a4882fe8bf19608968662be4fa');

INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('normal', 'normal', 'normal', '1993-05-10', 'F', 'normal', '317b32c143692b9939c197f6a5df54f9698df9a4882fe8bf19608968662be4fa');

INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('admin', 'admin', 'admin', '1993-05-10', 'F', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

select * from APPUSER;

