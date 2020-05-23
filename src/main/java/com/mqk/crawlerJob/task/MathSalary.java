package com.mqk.crawlerJob.task;

public class MathSalary {
	/**
	 *
	 * 获取薪水范围
	 */
	public static Integer[] getsalary(String salaryStr){
		Integer[] salary=new Integer[2];
		String date=salaryStr.substring(salaryStr.length()-1,salaryStr.length());
		if(!"月".equals(date) && !"年".equals(date)){
			salaryStr=salaryStr.substring(0,salaryStr.length()-2);
			salary[0]=salary[1]=str2Num(salaryStr,240);
			return salary;
		}
		String unit = salaryStr.substring(salaryStr.length() - 3, salaryStr.length() - 2);
		String[] salarys = salaryStr.substring(0, salaryStr.length() - 3).split("-");
		salary[0]=mathSalary(date,unit,salarys[0]);
		salary[1]=mathSalary(date,unit,salarys[1]);
		return salary;
	}

	private static Integer str2Num(String salaryStr, int num) {
		try {
			//必须用Number接受否则有精度丢失问题
			Number res=Float.parseFloat(salaryStr) * num;
			return res.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static Integer mathSalary(String date, String unit, String salaryStr) {
		Integer salary=0;
		if("万".equals(unit)){
			salary=str2Num(salaryStr,10000);
		}else{
			salary=str2Num(salaryStr,1000);
		}
		if("月".equals(date)){
			salary=str2Num(salary.toString(),12);
		}
		return salary;
	}

}
