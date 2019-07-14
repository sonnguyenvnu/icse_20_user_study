/** 
 * Create a period with a specified number of months. <p> The standard period type is used, thus you can add other fields such as years or days using the <code>withXxx()</code> methods. For example, <code>Period.months(2).withDays(6);</code> <p> If you want a month-based period that cannot have other fields added, then you should consider using  {@link Months}.
 * @param months  the amount of months in this period
 * @return the period
 */
public static Period months(int months){
  return new Period(new int[]{0,months,0,0,0,0,0,0},PeriodType.standard());
}
