CREATE DATABASE agenda_mvcs;

USE agenda_mvcs;

CREATE TABLE peliculas( 
    idp char(5) NOT NULL PRIMARY KEY,
    nomp varchar(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO peliculas (idp, nomp) VALUES 
('BE001','Bella'), 
('PI002','Pinocho'),
('LB003','La bestia');
 

SELECT * FROM peliculas;

CREATE USER 'user_mvc'@'localhost' IDENTIFIED BY 'pass_mvc.2018';
GRANT ALL PRIVILEGES ON agenda_mvc.* TO 'user_mvc'@'localhost';
FLUSH PRIVILEGES;

