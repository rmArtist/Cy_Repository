show databases;
select * from tables;

show variables like '%char%';
show variables like '%colla%';

alter database metastore default character set utf8;
ALTER DATABASE  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE COLUMNS_V2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE COLUMNS_V2 CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;