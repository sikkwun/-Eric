use keysystem;
CREATE OR REPLACE TABLE TBL_POLICY
(
	keyNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	username varchar(45), 
	description varchar(100), 
	minLen INT
);

use keysystem;
CREATE OR REPLACE TABLE TBL_CLAIM(
	keyNo varchar(100) NOT NULL PRIMARY KEY, 
	username varchar(45), 
	description varchar(100)
);