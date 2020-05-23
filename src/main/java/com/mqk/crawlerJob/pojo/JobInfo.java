package com.mqk.crawlerJob.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * +--------------+--------------+------+-----+---------+----------------+
 * | Field        | Type         | Null | Key | Default | Extra          |
 * +--------------+--------------+------+-----+---------+----------------+
 * | id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
 * | company_name | varchar(100) | YES  |     | NULL    |                |
 * | company_addr | varchar(200) | YES  |     | NULL    |                |
 * | company_info | text         | YES  |     | NULL    |                |
 * | job_name     | varchar(100) | YES  |     | NULL    |                |
 * | job_addr     | varchar(50)  | YES  |     | NULL    |                |
 * | job_info     | text         | YES  |     | NULL    |                |
 * | salary_min   | int(10)      | YES  |     | NULL    |                |
 * | salary_max   | int(10)      | YES  |     | NULL    |                |
 * | url          | varchar(150) | YES  |     | NULL    |                |
 * | time         | varchar(10)  | YES  |     | NULL    |                |
 * +--------------+--------------+------+-----+---------+----------------+
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class JobInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer salaryMin;
	private Integer salaryMax;
	private String companyName;
	private String companyAddr;
	private String companyInfo;
	private String jobName;
	private String jobAddr;
	private String jobInfo;
	private String url;
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobAddr() {
		return jobAddr;
	}

	public void setJobAddr(String jobAddr) {
		this.jobAddr = jobAddr;
	}

	public String getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
