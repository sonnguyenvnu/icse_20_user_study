/** 
 * Gets a type that defines all standard fields from days downwards. <ul> <li>days <li>hours <li>minutes <li>seconds <li>milliseconds </ul>
 * @return the period type
 */
public static PeriodType dayTime(){
  PeriodType type=cDTime;
  if (type == null) {
    type=new PeriodType("DayTime",new DurationFieldType[]{DurationFieldType.days(),DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.millis()},new int[]{-1,-1,-1,0,1,2,3,4});
    cDTime=type;
  }
  return type;
}
