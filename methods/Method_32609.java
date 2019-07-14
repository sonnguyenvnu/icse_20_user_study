/** 
 * Create a period with a specified number of years. <p> The standard period type is used, thus you can add other fields such as months or days using the <code>withXxx()</code> methods. For example, <code>Period.years(2).withMonths(6);</code> <p> If you want a year-based period that cannot have other fields added, then you should consider using  {@link Years}.
 * @param years  the amount of years in this period
 * @return the period
 */
public static Period years(int years){
  return new Period(new int[]{years,0,0,0,0,0,0,0},PeriodType.standard());
}
