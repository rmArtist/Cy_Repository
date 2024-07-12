package com.cy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseDeleteData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		创建HBase配置对象
		Configuration conf = HBaseConfiguration.create();
//		指定zookeeper集群地址
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
//		创建连接对象
		Connection conn = ConnectionFactory.createConnection(conf);
//		Table负责与记录相关的操作，如增删改查等
		TableName tableName = TableName.valueOf("t1");
		Table table = conn.getTable(tableName);
//		创建删除对象Delete,根据rowkey删除一整条
		Delete delete = new Delete(Bytes.toBytes("row1"));
		table.delete(delete);
//		释放资源
		table.close();
		System.out.println("delete data success!!");

	}

}
