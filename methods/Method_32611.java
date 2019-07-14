/** 
 * Create a period with a specified number of days. <p> The standard period type is used, thus you can add other fields such as months or weeks using the <code>withXxx()</code> methods. For example, <code>Period.days(2).withHours(6);</code> <p> If you want a day-based period that cannot have other fields added, then you should consider using  {@link Days}.
 * @param days  the amount of days in this period
 * @return the period
 */
public static Period days(int days){
  return new Period(new int[]{0,0,0,days,0,0,0,0},PeriodType.standard());
}
