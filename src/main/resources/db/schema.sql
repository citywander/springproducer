create table user_details (id integer not null auto_increment, email varchar(255), first_Name varchar(255), last_Name varchar(255), password varchar(255), primary key (id));
create table user_address (id integer not null auto_increment, address varchar(255), province varchar(255), user_id integer, created_by varchar(255), modified_by varchar(255), created TIMESTAMP , modified TIMESTAMP , primary key (id));