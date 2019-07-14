/** 
 * <p> Get a <code>Date</code> object that represents the given time, on today's date (equivalent to  {@link #dateOf(int,int,int)}). </p>
 * @param second The value (0-59) to give the seconds field of the date
 * @param minute The value (0-59) to give the minutes field of the date
 * @param hour The value (0-23) to give the hours field of the date
 * @return the new date
 */
public static Date todayAt(int hour,int minute,int second){
  return dateOf(hour,minute,second);
}
