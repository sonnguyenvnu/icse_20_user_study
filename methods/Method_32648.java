/** 
 * Gets a type that defines all standard fields except weeks. <ul> <li>years <li>months <li>days <li>hours <li>minutes <li>seconds <li>milliseconds </ul>
 * @return the period type
 */
public static PeriodType yearMonthDayTime(){
  PeriodType type=cYMDTime;
  if (type == null) {
    type=new PeriodType("YearMonthDayTime",new DurationFieldType[]{DurationFieldType.years(),DurationFieldType.months(),DurationFieldType.days(),DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.millis()},new int[]{0,1,-1,2,3,4,5,6});
    cYMDTime=type;
  }
  return type;
}
