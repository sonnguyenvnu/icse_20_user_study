/** 
 * ????  ?? ?? ? ???
 * @param time
 * @return
 */
public static int[] getTimeDetail(long time){
  final Calendar mCalendar=Calendar.getInstance();
  mCalendar.setTimeInMillis(time);
  return new int[]{mCalendar.get(Calendar.HOUR_OF_DAY),mCalendar.get(Calendar.MINUTE),mCalendar.get(Calendar.SECOND)};
}
