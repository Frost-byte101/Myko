create database watcher;

create table user(

    id int primary key auto_increment,
    username varchar(255),
    password varchar(255),
    identity varchar(255),
    status varchar (255)

)

create table relation(

     id int primary key auto_increment,
     teaid int,
     stuid int

)

create table task(

    id int primary key auto_increment,
    content varchar(255),
    starttime varchar(255),
    endtime varchar(255),
    stuid int,
    status varchar (255)

)