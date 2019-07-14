/** 
 * Whether the scheduler has been started.   <p> Note: This only reflects whether <code> {@link #start()}</code> has ever been called on this Scheduler, so it will return <code>true</code> even  if the <code>Scheduler</code> is currently in standby mode or has been  since shutdown. </p>
 * @see #start()
 * @see #isShutdown()
 * @see #isInStandbyMode()
 */
public boolean isStarted() throws SchedulerException {
  return (Boolean)getAttribute("Started");
}
