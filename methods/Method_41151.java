/** 
 * Set number of times for interval to repeat. <p>Note: if you want total count = 1 (at start time) + repeatCount</p>
 * @return the new DailyTimeIntervalScheduleBuilder
 */
public DailyTimeIntervalScheduleBuilder withRepeatCount(int repeatCount){
  this.repeatCount=repeatCount;
  return this;
}
