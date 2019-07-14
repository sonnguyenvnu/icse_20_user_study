/** 
 * Compute the <code>Date</code> that is 1 second after the Nth firing of  the given <code>Trigger</code>, taking the triger's associated  <code>Calendar</code> into consideration. The input trigger will be cloned before any work is done, so you need not worry about its state being altered by this method.
 * @param trigg The trigger upon which to do the work
 * @param cal The calendar to apply to the trigger's schedule
 * @param numTimes The number of next fire times to produce
 * @return the computed Date, or null if the trigger (as configured) will not fire that many times.
 */
public static Date computeEndTimeToAllowParticularNumberOfFirings(OperableTrigger trigg,org.quartz.Calendar cal,int numTimes){
  OperableTrigger t=(OperableTrigger)trigg.clone();
  if (t.getNextFireTime() == null) {
    t.computeFirstFireTime(cal);
  }
  int c=0;
  Date endTime=null;
  for (int i=0; i < numTimes; i++) {
    Date d=t.getNextFireTime();
    if (d != null) {
      c++;
      t.triggered(cal);
      if (c == numTimes)       endTime=d;
    }
 else {
      break;
    }
  }
  if (endTime == null)   return null;
  endTime=new Date(endTime.getTime() + 1000L);
  return endTime;
}
