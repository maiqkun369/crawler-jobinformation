package com.mqk.crawlerJob.service.Impl;

import com.mqk.crawlerJob.dao.JobInfoDao;
import com.mqk.crawlerJob.pojo.JobInfo;
import com.mqk.crawlerJob.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class JobInfoServiceImpl implements JobInfoService {
	@Autowired
	private JobInfoDao jobInfoDao;
	@Override
	@Transactional
	public void save(JobInfo jobInfo) {
		//根据url和发布时间查询数据
		JobInfo info = new JobInfo();
		info.setUrl(jobInfo.getUrl());
		info.setTime(jobInfo.getTime());
		//判断数据库中是否有已存在的数据
		List<JobInfo> joblist = findJobInfo(info);
		if(joblist.size()==0){
			//不存在
			jobInfoDao.saveAndFlush(jobInfo);
		}
	}

	@Override
	public List<JobInfo> findJobInfo(JobInfo jobInfo) {
		Example example=Example.of(jobInfo);
		return jobInfoDao.findAll(example);
	}
}
