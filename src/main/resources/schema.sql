CREATE TABLE person
(
    id int NOT NULL AUTO_INCREMENT,
    first_Name varchar(100) NOT NULL,
    last_Name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id int NOT NULL AUTO_INCREMENT,
    person_id int NOT NULL,
    product_Name varchar(100) NOT NULL,
    price double NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);