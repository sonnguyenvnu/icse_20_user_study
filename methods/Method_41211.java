/** 
 * Creates an in memory job store (<code> {@link RAMJobStore}</code>) The thread priority is set to Thread.NORM_PRIORITY
 * @param maxThreads The number of threads in the thread pool
 * @throws SchedulerException if initialization failed.
 */
public void createVolatileScheduler(int maxThreads) throws SchedulerException {
  SimpleThreadPool threadPool=new SimpleThreadPool(maxThreads,Thread.NORM_PRIORITY);
  JobStore jobStore=new RAMJobStore();
  this.createScheduler(threadPool,jobStore);
}
