create table persons
(
--     id int primary key GENERATED BY DEFAULT AS IDENTITY,
    login varchar(100) not null,
    password varchar(100) not null,
    primary key (login)
--     ListOfFiles file[]
);