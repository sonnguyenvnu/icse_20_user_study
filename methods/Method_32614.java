/** 
 * Create a period with a specified number of millis. <p> The standard period type is used, thus you can add other fields such as days or hours using the <code>withXxx()</code> methods. For example, <code>Period.millis(20).withSeconds(30);</code>
 * @param millis  the amount of millis in this period
 * @return the period
 */
public static Period millis(int millis){
  return new Period(new int[]{0,0,0,0,0,0,0,millis},PeriodType.standard());
}
