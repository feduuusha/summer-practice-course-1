SELECT p.name as name
from passenger p;

select c.name
from company c;

select *
from trip
where town_from = "Moscow";

SELECT name
from Passenger
where INSTR(name, "man") = LENGTH(name) - 2;

select count(*) as count
from trip
where plane = "TU-134";

SELECT name
from company
where id in (select company from trip where plane = "Boeing");

select distinct plane
from trip
where town_to = "Moscow";


select town_to, TIMEDIFF(time_in, time_out) as flight_time
from trip
where town_from = "Paris";

select c.name
from company c
where c.id in (select distinct t.company from trip t where t.town_from = "Vladivostok");

select *
from trip
where time_out >= "19000101100000"
  and time_out <= "19000101140000";

select name
from passenger
where length(name) = (select max(length(name)) from passenger);

select trip, count(passenger) as count
from pass_in_trip
group by trip;

select q.name
from (select p.name, count(p.name) as count from passenger p group by p.name) as q
where q.count > 1;

select t.town_to
from trip t
where t.id in (select pit.trip
               from pass_in_trip pit
               where pit.passenger in
                     (select p.id from passenger p where p.name = "Bruce Willis"));

select t.time_in
from trip t
where t.id in
      (select pit.trip
       from pass_in_trip pit
       where pit.passenger in
             (select p.id
              from passenger p
              where p.name = "Steve Martin"))
  and town_to = 'London';

select p.name, query.count
from (select pit.passenger, count(pit.passenger) as count
      from pass_in_trip pit
      group by pit.passenger) query,
     passenger p
where p.id = query.passenger
order by query.count desc, p.name;

select fm.member_name, fm.status, q.costs
from familymembers fm,
     (select sum(p.amount * p.unit_price) as costs,
             p.family_member              as fam_mem
      from payments p
      where p.date >= "2005:01:01:00:00:00"
        and p.date < "2006:01:01:00:00:00"
      group by p.family_member) q
where q.costs > 0
  and fm.member_id = q.fam_mem;

select member_name
from familymembers
where birthday = (select min(birthday) from familymembers);

select fm.status
from familymembers fm
where fm.member_id in (select p.family_member
                       from payments p
                       where p.good in (select g.good_id
                                        from goods g
                                        where g.good_name = "potato"));

select fm.status, fm.member_name, q.costs
from familymembers fm,
     (select p.family_member              as fam_mem,
             sum(p.amount * p.unit_price) as costs
      from payments p
      where p.good in
            (select g.good_id
             from goods g
             where g.type in
                   (select gt.good_type_id
                    from goodtypes as gt
                    where gt.good_type_name = "entertainment"))
      group by p.family_member) q
where fm.member_id = q.fam_mem;

select g.good_name
from goods g,
     (select p.good, count(p.good) as count from payments p group by good) q
where q.count > 1
  and q.good = g.good_id;

select member_name
from familymembers
where status = "Mother";

select unit_price, good_name
from payments,
     goods
where unit_price = (select max(p.unit_price) as unit_price
                    from payments p
                    where p.good in (select g.good_id
                                     from goods g
                                     where g.type =
                                           (select gt.good_type_id
                                            from goodtypes gt
                                            where gt.good_type_name = "delicacies")))
  and good = good_id;

select fm.member_name, q.costs
from familymembers fm,
     (select p.family_member, sum(p.amount * p.unit_price) as costs
      from payments p
      where p.date >= "2005:06:01"
        and p.date < "2005:07:01"
      group by p.family_member) q
where q.family_member = fm.member_id;

select g.good_name
from goods g
where g.good_id not in (select distinct p.good from payments p where p.date < "2006:01:01" and p.date >= "2005:01:01");

select gt.good_type_name
from goodtypes gt
where gt.good_type_id not in (select distinct g.type
                              from goods g
                              where g.good_id in (select p.good
                                                  from payments p
                                                  where p.date >= "2005:01:01"
                                                    and p.date < "2006:01:01"));

select good_type_name, q.costs
from (select type, sum(amount * unit_price) as costs
      from payments
               left join goods on good = good_id
      where date >= "2005:01:01"
        and date < "2006:01:01"
      group by type) q
         left join goodtypes on q.type = good_type_id;

select count(*) as count
from trip
where town_from = "Rostov"
  and town_to = "Moscow";

select distinct p.name
from passenger p
where p.id in
      (select pit.passenger
       from pass_in_trip pit
                right join trip t on pit.trip = t.id
       where t.plane = "TU-134"
         and t.town_to = "Moscow")

select t.id as trip, count(*) as count
from trip t
         right join pass_in_trip pit on pit.trip = t.id
group by t.id
order by count desc;

select ceiling(AVG(q.age)) - 1 as age
from (select TIMESTAMPDIFF(YEAR, fm.birthday, NOW()) as age from familymembers fm) q;

select avg(p.unit_price) as cost
from payments p
where p.good in (select g.good_id from goods g where g.good_name = "red caviar" or g.good_name = "black caviar")

select count(*) as count
from class
where instr(name, "10");

select count(distinct classroom) as count
from schedule s
where s.date >= "2019:09:02"
  and s.date < "2019:09:03";

select *
from student
where instr(address, "ul. Pushkina");

select min(TIMESTAMPDIFF(YEAR, birthday, now())) as year
from student;

select count(*) as count
from student
where first_name = "Anna";

select count(sic.student) as count
from student_in_class sic
         left join class c on sic.class = c.id
where c.name = "10 B";

select s.name as subjects
from schedule sc
         left join subject s on sc.subject = s.id
where sc.teacher in (select t.id
                     from teacher t
                     where t.last_name = "Romashkin"
                       and LEFT(t.first_name, 1) = "P"
                       and LEFT(t.middle_name, 1) = "P");

select start_pair
from timepair
order by start_pair
limit 3, 1;

select t.last_name
from teacher t
where t.id in (select distinct sc.teacher
               from schedule sc
                        left join subject s on sc.subject = s.id
               where s.name = "Physical Culture")
order by t.last_name;

select max(TIMESTAMPDIFF(YEAR, s.birthday, now()))
           as max_year
from student s
where s.id in
      (select sic.student
       from student_in_class sic
                left join class c on sic.class = c.id
       where instr(c.name, "10"));

select distinct classroom
from (select classroom, count(*) as count from schedule group by classroom) q
where q.count = (select count(*) as count from schedule group by classroom order by count desc limit 1);

select c.name
from class c
where c.id in
      (select sc.class
       from schedule sc
                left JOIN teacher t on t.id = sc.teacher
       where t.last_name = "Krauze")

select count(*) as count
from schedule sc
         left join teacher t on sc.teacher = t.id
where t.last_name = "Krauze"
  and sc.date = "2019:08:30";

select c.name, count(*) as count
from student_in_class sic
         left join class c on sic.class = c.id
group by c.name
order by count desc;

select round(
                       (select count(*)
                        from student_in_class sic
                                 left join class c on sic.class = c.id
                        where c.name = "10 A") /
                       ((select count(*)
                         from student_in_class sic
                                  left join class c on sic.class = c.id))
                   * 100, 4) as percent;

select floor((select count(*) from student where birthday >= "2000:01:01" and birthday < "2001:01:01") /
             (select count(*) from student) * 100) as percent;
