/** 
 * <p> Called by the <code> {@link Scheduler}</code> when a serious error has occured within the scheduler - such as repeated failures in the <code>JobStore</code>, or the inability to instantiate a <code>Job</code> instance when its <code>Trigger</code> has fired. </p> <p> The <code>getErrorCode()</code> method of the given SchedulerException can be used to determine more specific information about the type of error that was encountered. </p>
 */
@Override public void schedulerError(String msg,SchedulerException cause){
  System.err.println("*** " + msg);
  cause.printStackTrace();
}
