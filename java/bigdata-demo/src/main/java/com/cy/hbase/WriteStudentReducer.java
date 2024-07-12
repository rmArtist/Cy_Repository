package com.cy.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;
/**
 * 将数据写入到HBase另一张表student_new中
 * */	
public class WriteStudentReducer extends TableReducer<ImmutableBytesWritable,Put,NullWritable>{
/**
 * 接收map()方法的输出，参数key和values的类型需与map()方法的输出一致
 * */
	protected void reduce(ImmutableBytesWritable key,Iterable<Put> values,Context context) throws IOException,InterruptedException{
		for(Put put:values) {
			context.write(NullWritable.get(), put);
		}
	}
	public static void main(String[] args) {
	}

}
