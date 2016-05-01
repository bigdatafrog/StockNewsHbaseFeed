package com.hbase.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hbase_Feed {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main (String args[]) throws IOException{
		String Domain = "http://www.moneycontrol.com";
		String News_Link_Temp="";
		int i=0;
		Document RTNews = Jsoup.connect(Domain+"/news/channel/stocks-6.html").get();
		String Reco_Link = RTNews.select("div[class]").text();
		//System.out.println("=====>"+Reco_Link);
/*Get HTML Links*/
Elements links = RTNews.select("a[href]");
for (Element link : links){
	
		String News_Link = link.attr("href");
		String token [];
		String Stk_Symbol="";
		String News_required[];
		
		if(!News_Link.contains("http")){ 
				if(!News_Link.contains("javascript")){ 
						if(!News_Link.contains("video")){
							if(!News_Link.contains("stocks-6")){
								if(!News_Link_Temp.equalsIgnoreCase(News_Link)){
									if(i<=3){
									
									Document RTSPLNews = Jsoup.connect(Domain+News_Link).get();
									Elements hbase_link = RTSPLNews.select("a[href]");
									for (Element hbase_links : hbase_link){
										String hbase_Link = hbase_links.attr("href");
										if(hbase_Link.contains("stockpricequote")){
											token = hbase_Link.split("/");
									//		System.out.println(token[6]);
											Stk_Symbol=token[6];
										}
									}
									String News = RTSPLNews.text();
									News_required = News.split("Tags");
									System.out.println(News_required[0]);
									//System.out.println("################"+News_required[0]);
									WriteHbaseFeed CT = new WriteHbaseFeed();
									CT.hbase_feed(Stk_Symbol,News_required[0]);
									}
									i++;
								}
							}
							News_Link_Temp=News_Link;
						}	
				}
		}
	
}
}
}