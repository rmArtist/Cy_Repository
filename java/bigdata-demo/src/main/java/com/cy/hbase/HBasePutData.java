package com.cy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBasePutData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		创建HBase配置对象
		Configuration conf = HBaseConfiguration.create();
//		指定zookeeper集群地址
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
//		创建连接对象
		Connection conn = ConnectionFactory.createConnection(conf);
//		Table负责与记录相关的操作，如增删改查等
//		TableName tableName = TableName.valueOf("t1");
		TableName tableName = TableName.valueOf("student");
		Table table = conn.getTable(tableName);
//		设置rowkey1
//		Put put1 = new Put(Bytes.toBytes("row1"));
		Put put1 = new Put(Bytes.toBytes("001"));
//		添加列数据，指定列族、列名与列值
//		put1.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes("zhangsan"));
//		put1.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes("18"));
//		put1.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("address"),Bytes.toBytes("shanghai"));
		put1.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("zhangsan"));
		put1.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"),Bytes.toBytes("18"));
		put1.addColumn(Bytes.toBytes("info"),Bytes.toBytes("address"),Bytes.toBytes("shanghai"));
//		设置rowkey2
//		Put put2 = new Put(Bytes.toBytes("row2"));
		Put put2 = new Put(Bytes.toBytes("002"));
//		添加列数据，指定列族、列名与列值
//		put2.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes("lisi"));
//		put2.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes("19"));
//		put2.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("address"),Bytes.toBytes("jiangsu"));
		put2.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("lisi"));
		put2.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"),Bytes.toBytes("19"));
		put2.addColumn(Bytes.toBytes("info"),Bytes.toBytes("address"),Bytes.toBytes("jiangsu"));
//		设置rowkey3
//		Put put3 = new Put(Bytes.toBytes("row3"));
		Put put3 = new Put(Bytes.toBytes("003"));
//		添加列数据，指定列族、列名与列值
//		put3.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes("wangwu"));
//		put3.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes("20"));
//		put3.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("address"),Bytes.toBytes("zhejiang"));
		put3.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("wangwu"));
		put3.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"),Bytes.toBytes("20"));
		put3.addColumn(Bytes.toBytes("info"),Bytes.toBytes("address"),Bytes.toBytes("zhejiang"));
//		执行添加数据
		table.put(put1);
		table.put(put2);
		table.put(put3);
//		释放资源
		table.close();
		System.out.println("put data success!!");
		
	}

}
