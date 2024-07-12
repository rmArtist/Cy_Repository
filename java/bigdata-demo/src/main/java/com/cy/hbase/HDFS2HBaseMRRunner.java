package com.cy.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * mapreduce任务构建与执行
 * */
public class HDFS2HBaseMRRunner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		创建Configuration实例
		Configuration conf = HBaseConfiguration.create();
//		创建Job任务，指定任务名称
		Job job = Job.getInstance(conf,"hbase_mr_job2");
//		设置任务运行主类
		job.setJarByClass(HDFS2HBaseMRRunner.class);
//		设置文件输入路径，centos01为hadoop集群namenode节点的主机名
		Path inputPath = new Path("hdfs://centos01:9000/tmp/test_dir/cy_student.txt");
		FileInputFormat.addInputPath(job, inputPath);
//		设置Mapper类
		job.setMapperClass(ReadHDFSStudentMapper.class);
//		Mapper类输出的key的类型
		job.setMapOutputKeyClass(ImmutableBytesWritable.class);
//		Mapper类输出的value的类型
		job.setMapOutputValueClass(Put.class);
//		初始化reducer任务
		TableMapReduceUtil.initTableReducerJob(
				"student_new",    // 数据目的地表名
				WriteHBaseStudentReducer.class,
				job);             // 指定reducer类与任务job
//		设置reduce数量，最少1个
		job.setNumReduceTasks(1);
//		执行任务
		boolean isSuccess = job.waitForCompletion(true);
		if(!isSuccess) {
			throw new IOException("任务运行错误！！");
		}
		System.exit(isSuccess ? 0 : 1);
	}

}
