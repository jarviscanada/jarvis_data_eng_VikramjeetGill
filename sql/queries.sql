INSERT INTO cd.facilities (facid, Name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES (9, 'Spa', 20, 30, 100000, 800);

INSERT INTO cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

update cd.facilities set initialoutlay = 10000 where facid = 1;

update cd.facilities facs
    set
        membercost = facs2.membercost * 1.1,
        guestcost = facs2.guestcost * 1.1
    from (select * from cd.facilities where facid = 0) facs2
    where facs.facid = 1;

truncate cd.bookings;

delete from cd.members where memid = 37;

select facid, name, membercost, monthlymaintenance from cd.facilities where (membercost > 0 AND (membercost < (monthlymaintenance * 1/50)));

select * from cd.facilities where name like '%Tennis%';

SELECT * FROM cd.facilities where facid = 1 OR facid =5;

SELECT memid, surname, firstname, joindate FROM cd.members WHERE joindate >= '2012-09-01';

select surname 
		from cd.members
union
select name
		from cd.facilities;

select bks.starttime 
	from 
		cd.bookings bks
		inner join cd.members mems
			on mems.memid = bks.memid
	where 
		mems.firstname='David' 
		and mems.surname='Farrell';

select bks.starttime as start, facs.name as name
	from 
		cd.facilities facs
		inner join cd.bookings bks
			on facs.facid = bks.facid
	where 
		facs.name in ('Tennis Court 2','Tennis Court 1') and
		bks.starttime >= '2012-09-21' and
		bks.starttime < '2012-09-22'
order by bks.starttime;

select mems.firstname as memfname, mems.surname as memsname, recs.firstname as recfname, recs.surname as recsname
	from 
		cd.members mems
		left outer join cd.members recs
			on recs.memid = mems.recommendedby
order by memsname, memfname;

select distinct recs.firstname as firstname, recs.surname as surname
	from 
		cd.members mems
		inner join cd.members recs
			on recs.memid = mems.recommendedby
order by surname, firstname;

select distinct mems.firstname || ' ' ||  mems.surname as member,
	(select recs.firstname || ' ' || recs.surname as recommender 
		from cd.members recs 
		where recs.memid = mems.recommendedby
	)
	from 
		cd.members mems
order by member;

select recommendedby, count(*) 
	from cd.members
	where recommendedby is not null
	group by recommendedby
order by recommendedby;

select facid, sum(slots) as "Total Slots"
		from cd.bookings
		group by facid
order by facid;

SELECT facid, sum(slots) as "Total Slots" from cd.bookings where starttime <= '2012-10-01' AND starttime >= '2012-09-01' group by facid order by sum(slots);

SELECT facid, extract(month from starttime) as month, sum(slots) as "Total Slots" from cd.bookings where extract(year from starttime) = 2012 group by facid, month order by facid, month;

SELECT count(distinct memid) from cd.bookings;

SELECT mem.surname, mem.firstname, mem.memid, min(bks.starttime) as starttime from cd.members mem INNER JOIN cd.bookings bks on mem.memid = bks.memid where bks.starttime > '2012-09-01' GROUP BY mem.surname, mem.firstname, mem.memid ORDER BY mem.memid;

SELECT COUNT(*) over(), firstname, surname from cd.members order by joindate;

select row_number() over(order by joindate), firstname, surname
		from cd.members
order by joindate;

select facid, total from (
    select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
   		 from cd.bookings
   	 group by facid
    ) as ranked
    where rank = 1;

select surname || ', ' || firstname as name from cd.members;

SELECT memid, telephone FROM cd.members WHERE telephone LIKE '(%';

select substr (mems.surname,1,1) as letter, count(*) as count from cd.members mems group by letter order by letter;

