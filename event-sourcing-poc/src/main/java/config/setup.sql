CREATE DATABASE `library`;

CREATE TABLE `library`.`customer`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `event_type` ENUM('CREATE', 'UPDATE', 'DELETE') NOT NULL,
  `name` VARCHAR (255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `library`.`book`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `event_type` ENUM('CREATE', 'UPDATE', 'DELETE') NOT NULL,
  `name` VARCHAR (255) NOT NULL,
  `autor` VARCHAR (255) NULL,
  `quantity_avaliable` INT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `library`.`loan`(
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `loan_id` INT NOT NULL,
  `event_type` ENUM('CREATE', 'UPDATE', 'DELETE') NOT NULL,
  `customer_id` INT NOT NULL,
  `book_id` INT NULL,
  PRIMARY KEY (`event_id`)
);

-- INSERT INTO `customer` (`customer_id`, `event_type`, `name`) VALUES (0,'CREATE','Zeca');
-- SELECT event_type FROM customer WHERE customer_id = 0 ORDER BY event_id DESC LIMIT 1;