/** 
 * Set the trigger to fire on the given days of the week.
 * @param onDaysOfWeek a variable length list of Integers representing the days of the week, per the values 1-7 as defined by  {@link java.util.Calendar#SUNDAY} - {@link java.util.Calendar#SATURDAY}. 
 * @return the updated DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder onDaysOfTheWeek(Integer... onDaysOfWeek){
  Set<Integer> daysAsSet=new HashSet<Integer>(12);
  Collections.addAll(daysAsSet,onDaysOfWeek);
  return onDaysOfTheWeek(daysAsSet);
}
