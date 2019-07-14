/** 
 * Gets a type that defines all standard fields except months. <ul> <li>years <li>weeks <li>days <li>hours <li>minutes <li>seconds <li>milliseconds </ul>
 * @return the period type
 */
public static PeriodType yearWeekDayTime(){
  PeriodType type=cYWDTime;
  if (type == null) {
    type=new PeriodType("YearWeekDayTime",new DurationFieldType[]{DurationFieldType.years(),DurationFieldType.weeks(),DurationFieldType.days(),DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.millis()},new int[]{0,-1,1,2,3,4,5,6});
    cYWDTime=type;
  }
  return type;
}
