package com.hbase.examples;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.HTable;


public class WriteHbaseFeed
{
  public void hbase_feed(String symbol,String news)throws IOException{
	  System.out.println("Symbol---->"+symbol+"<-------"+"News---->"+news+"<-----------");
	  Configuration con = HBaseConfiguration.create();
		HTable htable = new HTable(con,"Stock_News_Hbase");
		Put p = new Put(Bytes.toBytes(symbol));
		p.add(Bytes.toBytes("Stock"),Bytes.toBytes("Symbol"),Bytes.toBytes(symbol));
		p.add(Bytes.toBytes("Stock"),Bytes.toBytes("news"),Bytes.toBytes(news));
		htable.put(p);
		htable.close();
  }
}












/*package com.hbase.examples;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class CreateTable {
public static void main(String[] args) {
// Try to create a Table with 2 column family (Title, Author)
HTableDescriptor descriptor = new HTableDescriptor("stocktable");
descriptor.addFamily(new HColumnDescriptor("ticker"));
descriptor.addFamily(new HColumnDescriptor("200DMA"));

try {
// Create a HBaseAdmin
//HBaseConfiguration config = new HBaseConfiguration();
Configuration conf = HBaseConfiguration.create();
HBaseAdmin admin = new HBaseAdmin(conf);

// Create table
admin.createTable(descriptor);
System.out.println("Table created…");
} catch (IOException e) {
System.out.println("IOError: cannot create Table.");
e.printStackTrace();
}
}
}*/