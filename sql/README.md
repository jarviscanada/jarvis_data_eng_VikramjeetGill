# Introduction

#SQL Queries

###### Table Setup (DDL)

###### Question 1: The club is adding a new facility - a spa. We need to add it into the facilities table. Use the following values: facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.

```sql
INSERT INTO cd.facilities (facid, Name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES (9, 'Spa', 20, 30, 100000, 800);
```

###### Questions 2: Let's try adding the spa to the facilities table again. This time, though, we want to automatically generate the value for the next facid, rather than specifying it as a constant. Use the following values for everything else: Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.


```sql
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;
```

###### Question 4: We want to alter the price of the second tennis court so that it costs 10% more than the first one. Try to do this without using constant values for the prices, so that we can reuse the statement if we want to.

```sql
update cd.facilities facs
    set
        membercost = facs2.membercost * 1.1,
        guestcost = facs2.guestcost * 1.1
    from (select * from cd.facilities where facid = 0) facs2
    where facs.facid = 1;
```

###### Question 5: As part of a clearout of our database, we want to delete all bookings from the cd.bookings table. How can we accomplish this?

```sql
truncate cd.bookings;
```

###### Question 6: We want to remove member 37, who has never made a booking, from our database. How can we achieve that?

```sql
delete from cd.members where memid = 37;
```

###### Question 7: How can you produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question. 

```sql
select facid, name, membercost, monthlymaintenance from cd.facilities where (membercost > 0 AND (membercost < (monthlymaintenance * 1/50)));
```

###### Question 8: How can you produce a list of all facilities with the word 'Tennis' in their name?

```sql
select * from cd.facilities where name like '%Tennis%';
```

###### Question 9: How can you retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator.

```sql
SELECT * FROM cd.facilities where facid = 1 OR facid = 5;
```

###### Question 10: How can you produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question.  

```sql
SELECT memid, surname, firstname, joindate FROM cd.members WHERE joindate >= '2012-09-01';
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```

###### Question 3: 

```sql
```
