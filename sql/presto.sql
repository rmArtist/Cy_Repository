select * from hive.test.student;

SELECT t1.sname as name,t3.cname as subject,t2.score
  from (select sid,sname from hive.test.student) t1
 inner join (select sid,cid,score from hive.test.score) t2
    on t1.sid = t2.sid
 inner join (select cid,cname from hive.test.course) t3
    on t2.cid = t3.cid ;

select * from hive.test.student_s;