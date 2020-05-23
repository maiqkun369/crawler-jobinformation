package com.mqk.crawlerJob.task.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class Proxytest implements PageProcessor {
	@Override
	public void process(Page page) {
		System.out.println(page.getHtml().toString());
	}
	private Site site=Site.me();
	@Override
	public Site getSite() {
		return site;
	}
	@Scheduled(fixedDelay = 1000)
	public void Process(){
		//创建一个下载器
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		//给下载器设置代理服务器的信息
		httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("183.32.225.236",47079),new Proxy("1.197.10.27",16521)));
		Spider.create(new Proxytest())
				.addUrl("http://ip.chinaz.com/getip.aspx")
				.setDownloader(httpClientDownloader)//设置下载器
				.run();
	}
}
