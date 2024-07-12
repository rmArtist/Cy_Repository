package com.cy;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;


/*
* 查询hdfs文件并输出
* */
public class FileSystemCat {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //设置hdfs访问地址
        conf.set("fs.default.name","hdfs://centos01:9000");
        //取得FileSystem文件系统实例
        FileSystem fs = FileSystem.get(conf);
        //打开文件输入流
        InputStream in = fs.open(new Path("hdfs:/user/hive/warehouse/test.db/student/etl_dt=20230101/Student.txt"));
        //输出文件内容
        IOUtils.copyBytes(in,System.out,4096,false);
        //关闭输入流
        IOUtils.closeStream(in);
    }
}
