-- Populate AppUser Table with Default Values
INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('normal', 'Mary', 'Smith', '1993-05-10', 'F', 'normal', '317b32c143692b9939c197f6a5df54f9698df9a4882fe8bf19608968662be4fa');
INSERT INTO APPUSER (uRole, uFirstName, uLastName, uDOB, uGender, uEmail, uPassword) 
	VALUES ('admin', 'John', 'White', '1966-08-23', 'M', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- Populate IndustryType Table with Default Values
INSERT INTO INDUSTRYTYPE VALUES ('Bank', 'Bank Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Building', 'Building Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Data Communication', 'Data Communication Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Education', 'Education Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Farm', 'Farm Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Health', 'Health Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Mining', 'Mining Industry');
INSERT INTO INDUSTRYTYPE VALUES ('Publishing', 'Publishing Industry');

-- Populate Customer Table with Default Values
INSERT INTO CUSTOMER (custABN, custName, custAddress, custCentralTel, custWebsite, custFoundedYear, iName, uId)
        VALUES ('46002510054', 'Apple Australia', 'PO Box A2629, Sydney South, NSW 1235', '133622', 'https://www.apple.com/au/', 1976, 'Data Communication', 1);
INSERT INTO CUSTOMER (custABN, custName, custAddress, custCentralTel, custWebsite, custFoundedYear, iName, uId)
        VALUES ('88000014675', 'Woolworths Carnegie', '11-29 Kokaribb Road, Carnegie, VIC 3163', '0383476515', 'https://www.woolworths.com.au/', 1924, 'Farm', 2);

-- Populate Contact Table with Default Values
INSERT INTO CONTACT (contFirstName, contLastName, contGender, contPosition, contPhoneNo, contEmail, custId) 
	VALUES ('Maria', 'Williams', 'F', 'CEO', '0458633114', 'mariawilliams@gmail.com', 1);

INSERT INTO CONTACT (contFirstName, contLastName, contGender, contPosition, contPhoneNo, contEmail, custId) 
	VALUES ('Neil', 'Robinson', 'M', 'CEO', '0465422338', 'neilrobinson@gmail.com', 2);
