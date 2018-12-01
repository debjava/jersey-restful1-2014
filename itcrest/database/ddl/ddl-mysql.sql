/******************* Scripts for MYSQL ******************/
/*---------- Table Dropping Scripts ----------------*/
drop table itcusers;
drop table itcregistration;
/*---------- Table Creation Scripts ----------------*/
create table itcusers
(
	id 			integer(3),
	username 	varchar(30),
	password 	varchar(30)
	
);

create table itcregistration
(
	id 			integer(3),
	firstname 	varchar(30),
	lastname 	varchar(30),
	username	varchar(30),
	password 	varchar(30),
	age			integer(2),
	email		varchar(30),
	phonenumber	varchar(10)
);


/*---------- Data Insertion Scripts ----------------*/
insert into itcusers values(1,'Deb','abcd1234');
insert into itcusers values(1,'Krishna','pqrs1234');
insert into itcusers values(1,'Nisith','uvwx1234');
insert into itcusers values(1,'Ramya','asdf1234');
commit;

/*---------- Data Selection Scripts ----------------*/

select * from itcusers;

select * from itcregistration;
