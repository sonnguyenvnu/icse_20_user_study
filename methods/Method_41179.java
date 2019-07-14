/** 
 * <p> Initialize the factory, providing a handle to the <code>Scheduler</code> that should be made available within the <code>JobRunShell</code> and the <code>JobExecutionContext</code> s within it, and a handle to the <code>SchedulingContext</code> that the shell will use in its own operations with the <code>JobStore</code>. </p>
 */
public void initialize(Scheduler sched) throws SchedulerConfigException {
  this.scheduler=sched;
}
