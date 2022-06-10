create schema helpDesk_app;

set search_path to  helpDesk_app; 


-- ***************************************************************************** app_departments

CREATE TABLE  app_departments
(
 id              int generated always as identity primary key,
 department_name varchar(50) unique not null
);

-- ***************************************************************************** app_users

CREATE TABLE  app_users
(

 id            int generated always as identity primary key,
 department_id int default 2,
 last_name     varchar(50) ,
 first_name    varchar(50) ,
 phone         varchar(15) ,
 email         varchar(50) NOT NULL,
 username      varchar(50) unique not null check (length(username) >= 8),
 password      varchar not null check (length(password) >= 8),
 reportsTo     int default 5,
 isadmin       boolean default 'false',
 
 CONSTRAINT user_manager
 FOREIGN KEY (reportsTo ) 
 REFERENCES app_users(id),

 CONSTRAINT user_department 
 FOREIGN KEY (department_id) 
 REFERENCES app_departments(id)
);



-- ***************************************************************************** app_ticket_types

CREATE TABLE  app_ticket_types
(
 id          int generated always as identity primary key,
 ticket_type varchar(50) NOT NULL
);

-- ***************************************************************************** app_ticket_status

CREATE TABLE  app_ticket_status
(
 id        int generated always as identity primary key,
 status    varchar(50) NOT NULL
);

-- ***************************************************************************** app_tickets

CREATE TABLE  app_tickets
(
 id                    int generated always as identity primary key,
 status_id             int NOT NULL,
 user_id               int NOT NULL,
 type_id               int NOT NULL,
 description           varchar(1000) NOT NULL,
 submitted_on          varchar(10),
 open_date_time        varchar(10),
 close_date_time       varchar(10),
 expected_work_hours   int NULL,
 assigned_to           int NULL,

 CONSTRAINT update_status 
 FOREIGN KEY (status_id) 
 REFERENCES app_ticket_status(id),

 CONSTRAINT update_ticket_type 
 FOREIGN KEY (type_id) 
 REFERENCES app_ticket_types(id),

 CONSTRAINT ticket_creator_fk 
 FOREIGN KEY (user_id) 
 REFERENCES app_users(id),

 CONSTRAINT assign_ticket_fk 
 FOREIGN KEY (assigned_to) 
 REFERENCES app_users(id)
);

-- ***************************************************************************** app_ticket_details

CREATE TABLE app_ticket_details
(
 id              int generated always as identity primary key,
 ticket_id       int NOT NULL,
 status_id       int NOT NULL,
 start_date_time varchar(10),
 end_date_time   varchar(10),

 CONSTRAINT update_status1 
 FOREIGN KEY (status_id) 
 REFERENCES app_ticket_status(id),

 CONSTRAINT get_ticket_details 
 FOREIGN KEY (ticket_id) 
 REFERENCES app_tickets(id)
);
--*****************************************************************************



-- ***************************************************************************** app_departments
insert into app_departments (department_name) values ('admin'), ('user'), ('support_tech');
-- ***************************************************************************** app_departments




-- ***************************************************************************** app_users
INSERT INTO app_users (department_id,last_name,first_name,phone,email,username,password,reportsTo,isadmin) VALUES
	 (1,'Kathleen','Kenneth','4567655670','Hankckeray@web.com','bmoylan0','cjgdzLk2d',1,'true'),
	 (3,'Quentin','Mavis','5634457554','Janiaw35@yahoo.com','ecolleran1','IA7VITbDwc5',5,'false'),
	 (2,'Booth','Lois','3456778066','Tinaac6@yahoo.com','bbrisdel2','048K85lf1',5,'false'),
	 (2,'Cassandra','Alistair','6474776734','Danieozonnet.net','gwheelerd3','7Q4BSSAA3k',5,'false'),
	 (1,'Hubert','Sylvester','5645890249','Ottpplegat@chello.net','thought4','OtqSnJ2EtuV',5,'true'),
	 (2,'Alberta','Kathleen','5432435643','Addyowle18@cox.net','jtenaunt5','k68OEe09fI',5,'false'),
	 (2,'Corey','Chet','9565367986','Shania9@facebook.com','pfleursfy6','qi03efed9K',5,'false'),
	 (2,'Kurt','Sheryl','4778453952','Jeropthorne3@gmx.net','emoinddfve7','d4pMZR3mN5AM',5,'false'),
	 (3,'Dora','Eve','5555622468','Waldoruse5@att.net','ddismore8d','FRg7rd03Xwt4',1,'false'),
	 (3,'Bertha','Jeremy','8854759355','Jefooner.03@ymail.com','ncockings9','UFZgSE8Nbps',1,'false'),
	 (2,'Charlie','Lawton','1178916699','Hopelmo04@hetnet.net','ghammertona','qdfr3tZD9w',5,'false'),
	 (2,'Audrey','Walter','7758090666','Vivianrack4@sbcglobal.net','ofeldbrinb','dsgvEGnTZn',5,'false'),
	 (2,'Tracy','Florence','9997853258','JohnsonBroo44@yahoo.com.au','ptunacliftc','9D6EwpEir5KF',5,'false'),
	 (3,'Laura','Winnifred','3257679752','IonaWlett1@mail.com','bmclucasd','bxThahv1hyrr',1,'false'),
	 (3,'Lawton','Arnold','3378907906','Clarrontiernet.net','ksholempooe','p2fZYkVrtur',1,'false'),
	 (3,'Rob','Tammy','2345563961','Alishainr4@bigpond.net','loldcroftf','d5IFePG7AQI',1,'false'),
	 (3,'Claire','Millicent','3893335645','RaymonSweene2@free.org','clodghang','w0Ir7HXkV',1,'false'),
	 (2,'Jaynie','Richie','4449678965','ZackacAskil19@ymail.com','fegarrhhyt','VRMLWUQgfyh',5,'false'),
	 (2,'Clare','Tracy','4425399797','Pelegppleton8@facebook.com','atapinikgi','e3rEYYWWclk',5,'false'),
	 (2,'Hilda','Richard','7745555655','Lillian.revani5@pple.com','dmilifkkfej','zywUG3X87EF',5,'false');
-- ***************************************************************************** app_users






-- ***************************************************************************** app_ticket_types
insert into app_ticket_types (ticket_type) values ('Low Priority'),('Medium Priority'),('High Priority');
-- ***************************************************************************** app_ticket_types







-- ***************************************************************************** app_ticket_status
INSERT INTO app_ticket_status
(status)
VALUES('Submitted'), ('Open'), ('Pending Assignment'), ('Processing'), ('Complete'), ('Closed');
-- ***************************************************************************** app_ticket_status







-- ***************************************************************************** app_tickets
INSERT INTO app_tickets (status_id,user_id,type_id,description,submitted_on,open_date_time,close_date_time,expected_work_hours,assigned_to) VALUES
	 (1,4,3,'boken mouse','2022-05-31',NULL,NULL,24,1),
	 (2,7,3,'name on account is incorrect','2022-06-01',NULL,NULL,48,1),
	 (3,8,3,'i need to update my address','2022-06-02',NULL,NULL,5,1),
	 (4,11,2,'broken key board','2022-06-03',NULL,NULL,24,1),
	 (5,12,2,'i have ebeen locked out of my account','2022-06-04',NULL,NULL,12,1);
-- ***************************************************************************** app_tickets





-- ***************************************************************************** app_ticket_details
INSERT INTO app_ticket_details (ticket_id,status_id,start_date_time,end_date_time) VALUES
	 (1,1,NULL,NULL),
	 (2,1,NULL,NULL),
	 (3,1,NULL,NULL),
	 (4,1,NULL,NULL),
	 (5,1,NULL,NULL);

-- ***************************************************************************** app_ticket_details

