/** 
 * Create a period with a specified number of hours. <p> The standard period type is used, thus you can add other fields such as months or days using the <code>withXxx()</code> methods. For example, <code>Period.hours(2).withMinutes(30);</code> <p> If you want a hour-based period that cannot have other fields added, then you should consider using  {@link Hours}.
 * @param hours  the amount of hours in this period
 * @return the period
 */
public static Period hours(int hours){
  return new Period(new int[]{0,0,0,0,hours,0,0,0},PeriodType.standard());
}
