create table Eredmeny
(
    id    integer not null
        constraint Eredmeny_pk
            primary key autoincrement,
    name  text    not null,
    score integer not null,
    date  text    not null,
    time  integer not null
);

