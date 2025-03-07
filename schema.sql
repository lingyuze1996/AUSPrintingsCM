-- Table Customer
DROP TABLE CONTACT;
DROP TABLE CUSTOMER;
DROP TABLE INDUSTRYTYPE;
DROP TABLE APPUSER;

CREATE TABLE CUSTOMER (
    custId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    custABN CHAR(11) NOT NULL,
    custName VARCHAR(30) NOT NULL,
    custAddress VARCHAR(80) NOT NULL,
    custCentralTel VARCHAR(10) NOT NULL,
    custWebsite VARCHAR(40),
    custFoundedYear INTEGER NOT NULL,
    iId Integer,
    uId INTEGER
);

ALTER TABLE CUSTOMER ADD CONSTRAINT customer_pk PRIMARY KEY (custId);
ALTER TABLE CUSTOMER ADD CONSTRAINT customer_abn_unique UNIQUE (custABN);

-- Table IndustryType
CREATE TABLE INDUSTRYTYPE (
    iId Integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    iName VARCHAR(20) NOT NULL,
    iDesc VARCHAR(50)
);

ALTER TABLE INDUSTRYTYPE ADD CONSTRAINT industrytype_pk PRIMARY KEY (iId);


-- Table Contact
CREATE TABLE CONTACT (
    contId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    contFirstName VARCHAR(20) NOT NULL,
    contLastName VARCHAR(20) NOT NULL,
    contGender CHAR(1) NOT NULL,
    contPosition VARCHAR(20) NOT NULL,
    contPhoneNo CHAR(10) NOT NULL,
    contEmail VARCHAR(40) NOT NULL,
    custId INTEGER NOT NULL
);

ALTER TABLE CONTACT
    ADD CONSTRAINT chk_contact_gender CHECK (contGender IN (
        'F',
        'M'
    ));
ALTER TABLE CONTACT ADD CONSTRAINT contact_pk PRIMARY KEY (contId);


-- Table AppUser
CREATE TABLE APPUSER (
    uId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    uRole VARCHAR(10) NOT NULL,
    uFirstName VARCHAR(20) NOT NULL,
    uLastName VARCHAR(20) NOT NULL,
    uDOB DATE NOT NULL,
    uGender CHAR(1) NOT NULL,
    uEmail VARCHAR(40) NOT NULL,
    uPassword CHAR(64) NOT NULL    
);

ALTER TABLE APPUSER
    ADD CONSTRAINT chk_user_gender CHECK (uGender IN (
        'F',
        'M'
    ));
ALTER TABLE APPUSER ADD CONSTRAINT appuser_pk PRIMARY KEY (uId);
ALTER TABLE APPUSER ADD CONSTRAINT appuser_email_unique UNIQUE (uEmail);


-- Foreign Key Constraints
ALTER TABLE CUSTOMER 
    ADD CONSTRAINT customer_uId_fk FOREIGN KEY (uId) 
        REFERENCES APPUSER (uId);

ALTER TABLE CUSTOMER 
    ADD CONSTRAINT customer_iId_fk FOREIGN KEY (iId) 
        REFERENCES INDUSTRYTYPE (iId);

ALTER TABLE CONTACT 
    ADD CONSTRAINT contact_custId_fk FOREIGN KEY (custId) 
        REFERENCES CUSTOMER (custId)
            ON DELETE CASCADE;