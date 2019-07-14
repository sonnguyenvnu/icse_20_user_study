public static List<Date> computeFireTimesBetween(OperableTrigger trigger,org.quartz.Calendar cal,Date from,Date to,int num){
  List<Date> lst=new LinkedList<>();
  OperableTrigger t=(OperableTrigger)trigger.clone();
  if (t.getNextFireTime() == null) {
    t.setStartTime(from);
    t.setEndTime(to);
    t.computeFirstFireTime(cal);
  }
  for (int i=0; i < num; i++) {
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
  return lst;
}
