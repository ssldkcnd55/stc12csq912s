--1��
select department_name as "�а� ��", category as "�迭"
from tb_department;

--2��
select department_name || '�� ������ ' || capacity || '�� �Դϴ�.' as "�а��� ����"
from tb_department;

--3��
select ts.STUDENT_NAME
from tb_department td,tb_student ts
where td.DEPARTMENT_NO = ts.DEPARTMENT_NO
and absence_yn = 'Y'
and td.DEPARTMENT_NAME = '������а�';

--4��
select student_name
from tb_student
where STUDENT_NO in ('A513079','A513090','A513091','A513110','A513119')
order by student_name desc;


--5��
select department_name,category
from tb_department
where capacity between 20 and 30;

--6��
select professor_name
from tb_professor
where department_no is null;

--7��
select student_name
from tb_student
where department_no is null;

--8��
select class_no
from tb_class
where PREATTENDING_CLASS_NO is not null;

--9��
select distinct category
from tb_department;

--10��
select student_no,student_name,student_ssn
from tb_student
where student_address like '%����%'
and entrance_date like '02%'
and absence_yn is not null;

