/** 
 * @Description : ?? jobDetail?CronTrigger
 * @Param : [scheduler]
 * @return : [scheduler]
 * @Author : aodeng
 * @Date : 19-5-15
 */
private void schedulejobs(Scheduler scheduler) throws SchedulerException {
  JobDetail jobDetail=JobBuilder.newJob(TestJob1.class).withIdentity("job","group").build();
  CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
  CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger","group").withSchedule(scheduleBuilder).build();
  scheduler.scheduleJob(jobDetail,cronTrigger);
}
