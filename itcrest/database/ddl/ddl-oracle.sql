---------- Table Dropping Scripts ----------------
drop table itcusers;
drop table itcregistration;
---------- Table Creation Scripts ----------------
create table itcusers
(
	id 			number(3,0),
	username 	varchar2(30),
	password 	varchar2(30)
	
);

create table itcregistration
(
	id 			number(3,0),
	firstname 	varchar2(30),
	lastname 	varchar2(30),
	username	varchar2(30),
	password 	varchar2(30),
	age			number(2,0),
	email		varchar2(30),
	phonenumber	varchar2(10)
);


---------- Data Insertion Scripts ----------------
insert into itcusers values(1,'Deb','abcd1234');
insert into itcusers values(1,'Krishna','pqrs1234');
insert into itcusers values(1,'Nisith','uvwx1234');
insert into itcusers values(1,'Ramya','asdf1234');
commit;

---------- Data Selection Scripts ----------------

select * from itcusers;

select * from itcregistration;
