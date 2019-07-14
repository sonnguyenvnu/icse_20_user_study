/** 
 * ???????
 * @param jobDetail
 * @param cronExpression
 * @return
 */
private static CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail,String cronExpression){
  CronTriggerFactoryBean factoryBean=new CronTriggerFactoryBean();
  factoryBean.setJobDetail(jobDetail);
  factoryBean.setCronExpression(cronExpression);
  return factoryBean;
}
