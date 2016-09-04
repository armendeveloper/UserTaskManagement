


create table  Users(
username VARCHAR(45) NOT NULL ,
password VARCHAR(45) NOT NULL ,
enabled TINYINT NOT NULL DEFAULT 1 ,
PRIMARY KEY (username)
);

create table UserRoles(
user_role_id int(11) NOT NULL AUTO_INCREMENT,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL,
PRIMARY KEY (user_role_id),
UNIQUE KEY uni_username_role (role,username),
KEY fk_username_idx (username),
CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES Users (username)
);


create table Projects(
id int primary key Auto_Increment,
name varchar(50) not null
);

create table Tasks(
id int primary key Auto_Increment,
name varchar(50) not null,
username VARCHAR(45) not null,
project_id int not null, 
FOREIGN  KEY(username) REFERENCES Users(username),
FOREIGN KEY(project_id) Projects(id)
);
