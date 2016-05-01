package com.hbase.examples;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Result;

public class ReadHbaseFeed {
	public static void main(String args[]) throws IOException{
		ReadHbaseFeed RHF = new ReadHbaseFeed();
		RHF.getHbaseFeed("hcltechnologies");
	}
	public void getHbaseFeed(String Stk_ticker) throws IOException{
		String ticker = Stk_ticker;
		Configuration con = HBaseConfiguration.create();
		HTable htable = new HTable(con,"Stock_News_Hbase");
		Get g = new Get(Bytes.toBytes(ticker));
		Result result = htable.get(g);
		byte [] value = result.getValue(Bytes.toBytes("Stock"),Bytes.toBytes("Symbol"));
		byte [] value1 = result.getValue(Bytes.toBytes("Stock"),Bytes.toBytes("news"));
		String Symbol = Bytes.toString(value1);
		System.out.println("Symbol--------->"+Symbol);
		
	}
}
