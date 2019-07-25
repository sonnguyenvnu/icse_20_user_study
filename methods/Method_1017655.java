/** 
 * Add this interval's value to the passed interval. This is backwards to what I would expect, but this makes it match the other existing add methods.
 * @param interval intval to add
 */
public void add(PGInterval interval){
  interval.setYears(interval.getYears() + getYears());
  interval.setMonths(interval.getMonths() + getMonths());
  interval.setDays(interval.getDays() + getDays());
  interval.setHours(interval.getHours() + getHours());
  interval.setMinutes(interval.getMinutes() + getMinutes());
  interval.setSeconds(interval.getSeconds() + getSeconds());
}
