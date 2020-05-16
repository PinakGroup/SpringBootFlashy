drop database if exists springbootflashy;
create database springbootflashy;
use springbootflashy;

create table app_user(
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(150) NOT NULL,
password VARCHAR(150) NOT NULL,
useremail VARCHAR(150) NOT NULL,
userfirstname VARCHAR(150) NOT NULL,
userlastname VARCHAR(150) NOT NULL,
useraddress VARCHAR(150) NOT NULL,
userenabled BOOLEAN,
userconfirmationtoken VARCHAR(150),
userdatecreated TIMESTAMP,
usercreatedby VARCHAR(150),
userdatemodified TIMESTAMP,
usermodifiedby VARCHAR(150),

PRIMARY KEY (id),
UNIQUE (username)) ENGINE=InnoDB;

create table app_role(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
PRIMARY KEY (id),
UNIQUE (name)) ENGINE=InnoDB;
   
CREATE TABLE app_user_role (
id BIGINT NOT NULL AUTO_INCREMENT,
userid BIGINT NOT NULL,
roleid INT NOT NULL,
PRIMARY KEY (id))ENGINE=InnoDB;

create table persistent_logins (
username varchar(64) not null,
series varchar(64) primary key,
token varchar(64) not null,
last_used timestamp not null)ENGINE=InnoDB;
 

ALTER TABLE app_user_role ADD CONSTRAINT FK_AURUSERID FOREIGN KEY (userid) REFERENCES app_user (id);
ALTER TABLE app_user_role ADD CONSTRAINT FK_AURROLEID FOREIGN KEY (roleid) REFERENCES app_role (id); 


INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin@mydomain.com', 'admin@admin', 'admin@admin', 'admin@admin', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');    
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin1', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin1@mydomain.com', 'admin1@admin1', 'admin1@admin1', 'admin1@admin1', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin2', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin2@mydomain.com', 'admin2@admin2', 'admin2@admin2', 'admin2@admin2', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin3', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin3@mydomain.com', 'admin3@admin3', 'admin3@admin3', 'admin3@admin3', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin4', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin4@mydomain.com', 'admin4@admin4', 'admin4@admin4', 'admin4@admin4', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin5', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin5@mydomain.com', 'admin5@admin5', 'admin5@admin5', 'admin5@admin5', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin6', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin6@mydomain.com', 'admin6@admin6', 'admin6@admin6', 'admin6@admin6', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin7', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin7@mydomain.com', 'admin7@admin7', 'admin7@admin7', 'admin7@admin7', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin8', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin8@mydomain.com', 'admin8@admin8', 'admin8@admin8', 'admin8@admin8', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin9', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin9@mydomain.com', 'admin9@admin9', 'admin9@admin9', 'admin9@admin9', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  ;
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin10', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin10@mydomain.com', 'admin10@admin10', 'admin10@admin10', 'admin10@admin10', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin11', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin11@mydomain.com', 'admin11@admin11', 'admin11@admin11', 'admin11@admin11', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin12', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin12@mydomain.com', 'admin12@admin12', 'admin12@admin12', 'admin12@admin12', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin13', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin13@mydomain.com', 'admin13@admin13', 'admin13@admin13', 'admin13@admin13', FALSE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin14', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin14@mydomain.com', 'admin14@admin14', 'admin14@admin14', 'admin14@admin14', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  
INSERT INTO `springbootflashy`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`, `userenabled`, `userconfirmationtoken`, `userdatecreated`, `usercreatedby`, `userdatemodified`, `usermodifiedby`) VALUES ('admin15', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin15@mydomain.com', 'admin15@admin15', 'admin15@admin15', 'admin15@admin15', TRUE, 'abcd-efgh-ijkl-mnop', '2008-01-01 00:00:01', 'admin@admin', '2008-01-01 00:00:01', 'admin@admin');  

INSERT INTO `springbootflashy`.`app_role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `springbootflashy`.`app_role` (`id`, `name`) VALUES ('2', 'EDITOR');
INSERT INTO `springbootflashy`.`app_role` (`id`, `name`) VALUES ('3', 'VIEWER');


INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('2', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('2', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('3', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('3', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('4', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('4', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('5', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('6', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('8', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('9', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('10', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('11', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '3');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '1');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '2');
INSERT INTO `springbootflashy`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '3');

