/** 
 * <p> Initialize the factory, providing a handle to the <code>Scheduler</code> that should be made available within the <code>JobRunShell</code> and the <code>JobExecutionContext</code> s within it. </p>
 */
public void initialize(Scheduler sched){
  this.scheduler=sched;
}
