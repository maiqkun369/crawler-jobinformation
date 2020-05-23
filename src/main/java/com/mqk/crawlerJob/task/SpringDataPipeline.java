package com.mqk.crawlerJob.task;

import com.mqk.crawlerJob.pojo.JobInfo;
import com.mqk.crawlerJob.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component
public class SpringDataPipeline implements Pipeline {

	@Autowired
	private JobInfoService jobInfoService;
	@Override
	public void process(ResultItems resultItems, Task task) {
		JobInfo jobInfo = resultItems.get("jobInfo");
		if(jobInfo!=null){
			jobInfoService.save(jobInfo);
		}
	}
}
