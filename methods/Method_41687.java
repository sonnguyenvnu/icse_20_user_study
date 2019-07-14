/** 
 * Returns a list of Dates that are the next fire times of a  <code>Trigger</code>. The input trigger will be cloned before any work is done, so you need not worry about its state being altered by this method.
 * @param trigg The trigger upon which to do the work
 * @param cal The calendar to apply to the trigger's schedule
 * @param numTimes The number of next fire times to produce
 * @return List of java.util.Date objects
 */
public static List<Date> computeFireTimes(OperableTrigger trigg,org.quartz.Calendar cal,int numTimes){
  LinkedList<Date> lst=new LinkedList<Date>();
  OperableTrigger t=(OperableTrigger)trigg.clone();
  if (t.getNextFireTime() == null) {
    t.computeFirstFireTime(cal);
  }
  for (int i=0; i < numTimes; i++) {
    Date d=t.getNextFireTime();
    if (d != null) {
      lst.add(d);
      t.triggered(cal);
    }
 else {
      break;
    }
  }
  return java.util.Collections.unmodifiableList(lst);
}
