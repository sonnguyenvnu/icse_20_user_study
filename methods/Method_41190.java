private void removeExcludedDay(java.util.Calendar day,boolean isChecked){
  if (!isChecked && !isDayExcluded(day)) {
    return;
  }
  if (this.excludeDays.remove(day)) {
    return;
  }
  int dmonth=day.get(java.util.Calendar.MONTH);
  int dday=day.get(java.util.Calendar.DAY_OF_MONTH);
  Iterator<java.util.Calendar> iter=excludeDays.iterator();
  while (iter.hasNext()) {
    java.util.Calendar cl=(java.util.Calendar)iter.next();
    if (dmonth != cl.get(java.util.Calendar.MONTH)) {
      continue;
    }
    if (dday != cl.get(java.util.Calendar.DAY_OF_MONTH)) {
      continue;
    }
    day=cl;
    break;
  }
  this.excludeDays.remove(day);
}
