/** 
 * Create a period with a specified number of seconds. <p> The standard period type is used, thus you can add other fields such as days or hours using the <code>withXxx()</code> methods. For example, <code>Period.seconds(2).withMillis(30);</code> <p> If you want a second-based period that cannot have other fields added, then you should consider using  {@link Seconds}.
 * @param seconds  the amount of seconds in this period
 * @return the period
 */
public static Period seconds(int seconds){
  return new Period(new int[]{0,0,0,0,0,0,seconds,0},PeriodType.standard());
}
