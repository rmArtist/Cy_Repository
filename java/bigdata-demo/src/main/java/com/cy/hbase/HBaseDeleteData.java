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
//		创建HBase配置对象
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
		Connection conn = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf("t1");
		Table table = conn.getTable(tableName);
//		创建删除对象
		Delete delete = new Delete(Bytes.toBytes("row1"));
		table.delete(delete);
		table.close();
		System.out.println("delete data success!!");

	}

}
