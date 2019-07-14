/** 
 * <p> Redefine a certain day to be excluded (true) or included (false). </p>
 */
public void setDayExcluded(java.util.Calendar day,boolean exclude){
  if (exclude) {
    if (isDayExcluded(day)) {
      return;
    }
    excludeDays.add(day);
    dataSorted=false;
  }
 else {
    if (!isDayExcluded(day)) {
      return;
    }
    removeExcludedDay(day,true);
  }
}
