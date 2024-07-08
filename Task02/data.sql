insert into car (mark, number, year_of_manufacture, model)
values ('Honda', 'O777OO116', 2007, 'Civik'),
       ('Lada', 'E404EE016', 2014, 'Granta'),
       ('BMW', 'M707KA716', 2019, 'M4');

insert into driver (first_name, second_name, passport_series, passport_number, driver_car)
values ('Алексей', 'Левтеров', 5315, 900879, 2),
       ('Мухаммед', 'Абзалов', 1280, 773670, 2),
       ('Ксения', 'Шилова', 6543, 443185, 1),
       ('Богдан', 'Кондрахин', 5617, 093679, 3);

insert into client (first_name, second_name, age, number)
values ('Василий', 'Степанов', 22, '+79653425109'),
       ('Николай', 'Малинов', 52, '+79546395638');

insert into client (first_name, second_name, age)
values ('Владимир', 'Консантинов', 18);

update client set number = '+79673546781' where id = '3';

insert into "order" (client_id, driver_id, time_of_order, date_of_order)
values (2, 1, '12:55:33', '2024-07-17'),
       (2, 2, '15:30:00', '2024-07-17'),
       (3, 1, '14:15:00', '2024-07-18'),
       (1, 3, '04:20:15', '2024-07-19');

update car set year_of_manufacture = 2022 where model = 'BMW' and mark = 'M4';
