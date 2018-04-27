--1번
select department_name as "학과 명", category as "계열"
from tb_department;

--2번
select department_name || '의 정원은 ' || capacity || '명 입니다.' as "학과별 정원"
from tb_department;

--3번
select ts.STUDENT_NAME
from tb_department td,tb_student ts
where td.DEPARTMENT_NO = ts.DEPARTMENT_NO
and absence_yn = 'Y'
and td.DEPARTMENT_NAME = '국어국문학과';

--4번
select student_name
from tb_student
where STUDENT_NO in ('A513079','A513090','A513091','A513110','A513119')
order by student_name desc;


--5번
select department_name,category
from tb_department
where capacity between 20 and 30;

--6번
select professor_name
from tb_professor
where department_no is null;

--7번
select student_name
from tb_student
where department_no is null;

--8번
select class_no
from tb_class
where PREATTENDING_CLASS_NO is not null;

--9번
select distinct category
from tb_department;

--10번
select student_no,student_name,student_ssn
from tb_student
where student_address like '%전주%'
and entrance_date like '02%'
and absence_yn is not null;

