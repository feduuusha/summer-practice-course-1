drop table if exists "order";
drop table if exists client;
drop table if exists driver;
drop table if exists car;

create table car (
                     id serial primary key,
                     mark char(50) not null,
                     number char(9) unique not null,
                     year_of_manufacture integer check(1970 < year_of_manufacture and car.year_of_manufacture <= 2024) not null
);

alter table car add
    model char(50) not null default 'standard';

create table driver (
                        id serial primary key,
                        first_name char(30) not null,
                        second_name char(50) not null,
                        passport_series integer not null,
                        passport_number integer not null,
                        driver_car integer not null,
                        foreign key (driver_car) references car(id)
);

create table client (
                        id serial primary key,
                        first_name char(30) not null,
                        second_name char(50) not null,
                        age integer check(age >= 18 and age < 120) not null
);

alter table client add number char(20) default '+7' not null;

create table "order" (
                         id serial primary key,
                         client_id integer not null,
                         driver_id integer not null,
                         time_of_order time not null,
                         foreign key (client_id) references client(id),
                         foreign key (driver_id) references driver(id)
);

alter table "order" add date_of_order date;
