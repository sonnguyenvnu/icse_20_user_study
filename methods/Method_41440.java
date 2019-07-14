/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void addJob(JobDetail jobDetail,boolean replace,boolean storeNonDurableWhileAwaitingScheduling) throws SchedulerException {
  invoke("addJob",new Object[]{JobDetailSupport.toCompositeData(jobDetail),replace,storeNonDurableWhileAwaitingScheduling},new String[]{CompositeData.class.getName(),boolean.class.getName(),boolean.class.getName()});
}
