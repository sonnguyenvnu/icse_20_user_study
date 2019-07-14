/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public void startDelayed(int seconds) throws SchedulerException {
  try {
    getRemoteScheduler().startDelayed(seconds);
  }
 catch (  RemoteException re) {
    throw invalidateHandleCreateException("Error communicating with remote scheduler.",re);
  }
}
