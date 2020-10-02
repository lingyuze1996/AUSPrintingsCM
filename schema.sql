DROP TABLE STAFF;
CREATE TABLE STAFF (
    sId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    sRole VARCHAR(10) NOT NULL,
    sFirstName VARCHAR(20) NOT NULL,
    sLastName VARCHAR(20) NOT NULL,
    sDOB CHAR(10) NOT NULL,
    sGender CHAR(1) NOT NULL,
    sEmail VARCHAR(40) NOT NULL,
    sPassword CHAR(64) NOT NULL    
);

ALTER TABLE STAFF
    ADD CONSTRAINT chk_gender CHECK (sGender IN (
        'F',
        'M'
    ));
ALTER TABLE STAFF ADD CONSTRAINT staff_pk PRIMARY KEY ( sId );
ALTER TABLE STAFF ADD CONSTRAINT email_unique UNIQUE (sEmail);

INSERT INTO STAFF (sRole, sFirstName, sLastName, sDOB, sGender, sEmail, sPassword) 
	VALUES ('admin', 'Yuze', 'Ling', '1996-06-13', 'M', 'ylin0081@student.monash.edu', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO STAFF (sRole, sFirstName, sLastName, sDOB, sGender, sEmail, sPassword) 
	VALUES ('normal', 'John', 'Smith', '1993-05-10', 'F', 'johnsmith@gmail.com', '317b32c143692b9939c197f6a5df54f9698df9a4882fe8bf19608968662be4fa');

INSERT INTO STAFF (sRole, sFirstName, sLastName, sDOB, sGender, sEmail, sPassword) 
	VALUES ('normal', 'normal', 'normal', '1993-05-10', 'F', 'normal', '317b32c143692b9939c197f6a5df54f9698df9a4882fe8bf19608968662be4fa');

INSERT INTO STAFF (sRole, sFirstName, sLastName, sDOB, sGender, sEmail, sPassword) 
	VALUES ('admin', 'admin', 'admin', '1993-05-10', 'F', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

select * from staff;

