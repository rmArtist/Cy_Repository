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
		// TODO Auto-generated method stub
//		创建hbase配置对象
		Configuration conf = HBaseConfiguration.create();
//		指定zookeeper集群地址
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
//		创建连接对象
		Connection conn = ConnectionFactory.createConnection(conf);
//		得到数据库管理员对象
		Admin admin = conn.getAdmin();
//		表描述信息
//		指定表名为"t1",TableName表示表名的不可变POJO类
//		TableName tableName = TableName.valueOf("t1");
		TableName tableName = TableName.valueOf("student");
//		创建表描述构造器
		TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
//		创建列族描述器，指定列族名称为"f1"
//		ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("f1")).build();
		ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info")).build();
//		向表描述构造器中添加列族
		tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
//		创建表描述器
		TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
		
//		请求HBase,执行创建表命令
		admin.createTable(tableDescriptor);
		System.out.println("create table success!!");
	}

}
