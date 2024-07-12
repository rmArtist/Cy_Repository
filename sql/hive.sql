drop table test.test_avater_dt;
create external table test.test_avater_dt(
 number string comment'号码'
,name string comment'姓名'
,avatar string comment'组别'
)
partitioned by (etl_dt string comment '数据日期')
row format delimited 
fields terminated by '\t'
lines terminated by '\n'
null defined as ' '
stored as textfile
;
select * from test.test_avater_dt;
load data inpath '/input/csv_to_hive_file' into table test.test_avater_dt partition(etl_dt=20210101);