create table files
(
--     id bigint primary key GENERATED BY DEFAULT AS IDENTITY,
    fileName varchar(100) not null,
    size bigint not null,
    date DATE not null,
    content bytea,
    person varchar(100),
    primary key (fileName)
);
