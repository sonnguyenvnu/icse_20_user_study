/** 
 * Returns a list of Dates that are the next fire times of a  <code>Trigger</code> that fall within the given date range. The input trigger will be cloned before any work is done, so you need not worry about its state being altered by this method. <p> NOTE: if this is a trigger that has previously fired within the given date range, then firings which have already occurred will not be listed in the output List. </p>
 * @param trigg The trigger upon which to do the work
 * @param cal The calendar to apply to the trigger's schedule
 * @param from The starting date at which to find fire times
 * @param to The ending date at which to stop finding fire times
 * @return List of java.util.Date objects
 */
public static List<Date> computeFireTimesBetween(OperableTrigger trigg,org.quartz.Calendar cal,Date from,Date to){
  LinkedList<Date> lst=new LinkedList<Date>();
  OperableTrigger t=(OperableTrigger)trigg.clone();
  if (t.getNextFireTime() == null) {
    t.setStartTime(from);
    t.setEndTime(to);
    t.computeFirstFireTime(cal);
  }
  while (true) {
    Date d=t.getNextFireTime();
    if (d != null) {
      if (d.before(from)) {
        t.triggered(cal);
        continue;
      }
      if (d.after(to)) {
        break;
      }
      lst.add(d);
      t.triggered(cal);
    }
 else {
      break;
    }
  }
  return java.util.Collections.unmodifiableList(lst);
}
