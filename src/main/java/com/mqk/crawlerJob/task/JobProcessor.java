package com.mqk.crawlerJob.task;

import com.mqk.crawlerJob.pojo.JobInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.lang.reflect.Array;
import java.util.List;

@Component
public class JobProcessor implements PageProcessor {
	private String url="http://search.51job.com/list/000000,000000,0000,00,9,99,Java%2B%25E9%25AB%2598%25E7%25BA%25A7,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
	private Site site=Site.me()
			.setCharset("gbk")
			.setTimeOut(10*1000)
			.setRetrySleepTime(3000)
			.setRetryTimes(3);
	@Override
	public void process(Page page) {
		//解析页面，获取招聘信息详情的url地址
		List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
		//判断获取到的集合是否为空
		if(list.size()==0){
			//如果为空，表示这是招聘详情页
			saveJobInfo(page);
		}else{
			//如果不为空，表示这是列表项
			for (Selectable selectable : list) {
				//获取url
				String jobInfoUrl = selectable.links().toString();
				//把获取到的url地址放入任务队列中去
				page.addTargetRequest(jobInfoUrl);
			}
			//获取下一页的url
			String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
			//放入任务队列中
			page.addTargetRequest(bkUrl);
		}
		String html = page.getHtml().toString();
		System.out.println("1234");
	}
	//解析页面，获取招聘详情信息，保存数据
	private void saveJobInfo(Page page) {
		//创建招聘详情对象
		JobInfo info = new JobInfo();
		//解析页面
		Html html = page.getHtml();
		//获取数据，封装到对象中
		info.setUrl(page.getUrl().toString());
		info.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
		info.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
		info.setCompanyName(html.css("div.cn p.cname a","text").toString());
		info.setJobAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
		info.setJobName(html.css("div.cn h1","text").toString());
		info.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
		//获取薪资
		String s1 = Jsoup.parse(html.css("strong[title]").toString()).text();
		Integer[] salary = MathSalary.getsalary(s1);
		info.setSalaryMax(salary[1]);
		info.setSalaryMin(salary[0]);
		//获得发布时间
		String time = Jsoup.parse(html.css("p.msg.ltype").regex(".*发布").toString()).text();
		String[] s = time.split("|");
		StringBuffer sb = new StringBuffer();
		for (int i = s.length-7; i <s.length-2 ; i++) {
			sb.append(s[i]);
		}
		String timeValue = sb.toString();
		info.setTime(timeValue);
		//把结果保存起来
		page.putField("jobInfo",info);
	}

	@Override
	public Site getSite() {
		return site;
	}
	@Autowired
	private SpringDataPipeline springDataPipeline;

	//@Scheduled(initialDelay = 1000,fixedDelay = 100*1000)
	public void process(){
		Spider.create(new JobProcessor())
				.addUrl(url)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
				.thread(10)
				.addPipeline(springDataPipeline)
				.run();
	}
}
