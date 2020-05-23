package com.mqk.crawlerJob.dao;

import com.mqk.crawlerJob.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoDao extends JpaRepository<JobInfo,Long> {
}
