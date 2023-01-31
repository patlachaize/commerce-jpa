DROP SCHEMA IF EXISTS commerce;
CREATE SCHEMA commerce;
CREATE USER if not exists 'adm'@'localhost' IDENTIFIED BY 'adm';
GRANT ALL PRIVILEGES ON commerce.* TO 'adm'@'localhost';
USE commerce

CREATE TABLE clients (
    num integer auto_increment,
    prenom VARCHAR(10) unique,
    solde numeric(5,2),
    check (solde >= 0),
    PRIMARY KEY(num)
);

INSERT INTO clients VALUES (null,'Jean', 100);
INSERT INTO clients VALUES (null,'Marie', 120);
SELECT * from clients;

CREATE TABLE items (
    num integer auto_increment,
    description VARCHAR(20),
    prix numeric(5,2),
    client integer,
    PRIMARY KEY(num),
    FOREIGN KEY(client) references clients(num)
);
INSERT INTO items VALUES (null,'tournevis', 12.5,null);
INSERT INTO items VALUES (null,'marteau', 20,null);
INSERT INTO items VALUES (null,'boite de 100 clous',10,1);
INSERT INTO items VALUES (null,'boite de 100 clous',10,null);
INSERT INTO items VALUES (null,'boite de 1000 clous',100,null);
SELECT * from items;
SELECT i.num,i.description,i.prix,
       i.client, c.prenom, c.solde
FROM items i
         LEFT OUTER JOIN clients c ON c.num = i.client;