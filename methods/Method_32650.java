/** 
 * Gets a type that defines year, week and day fields. <ul> <li>years <li>weeks <li>days </ul>
 * @return the period type
 * @since 1.1
 */
public static PeriodType yearWeekDay(){
  PeriodType type=cYWD;
  if (type == null) {
    type=new PeriodType("YearWeekDay",new DurationFieldType[]{DurationFieldType.years(),DurationFieldType.weeks(),DurationFieldType.days()},new int[]{0,-1,1,2,-1,-1,-1,-1});
    cYWD=type;
  }
  return type;
}
