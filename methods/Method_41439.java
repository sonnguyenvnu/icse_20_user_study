/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void addJob(JobDetail jobDetail,boolean replace) throws SchedulerException {
  invoke("addJob",new Object[]{JobDetailSupport.toCompositeData(jobDetail),replace},new String[]{CompositeData.class.getName(),boolean.class.getName()});
}
