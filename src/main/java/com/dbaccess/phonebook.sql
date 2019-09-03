create database phonebook;
use phonebook;
create table Owners (
  id int not null auto_increment primary key,
  name varchar(128) not null,
  phoneNumber varchar(32)
);