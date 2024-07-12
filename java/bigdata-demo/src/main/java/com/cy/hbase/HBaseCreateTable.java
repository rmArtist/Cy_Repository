package com.cy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseCreateTable {

	public static void main(String[] args) throws Exception {
//		创建对象
		Configuration conf = HBaseConfiguration.create();
//		指定地址
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
		Connection conn = ConnectionFactory.createConnection(conf);
		Admin admin = conn.getAdmin();
//		表描述信息
		TableName tableName = TableName.valueOf("student");
		TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
		ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info")).build();
		tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
		TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
//		执行创建表
		admin.createTable(tableDescriptor);
		System.out.println("create table success!!");
	}

}
