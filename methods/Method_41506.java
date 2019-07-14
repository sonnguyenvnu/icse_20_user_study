/** 
 * <p> Called by the scheduler at the time a <code>Trigger</code> is first added to the scheduler, in order to have the <code>Trigger</code> compute its first fire time, based on any associated calendar. </p> <p> After this method has been called, <code>getNextFireTime()</code> should return a valid answer. </p>
 * @return the first time at which the <code>Trigger</code> will be firedby the scheduler, which is also the same value <code>getNextFireTime()</code> will return (until after the first firing of the <code>Trigger</code>). </p>
 */
@Override public Date computeFirstFireTime(org.quartz.Calendar calendar){
  nextFireTime=getFireTimeAfter(new Date(getStartTime().getTime() - 1000l));
  while (nextFireTime != null && calendar != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {
    nextFireTime=getFireTimeAfter(nextFireTime);
  }
  return nextFireTime;
}
