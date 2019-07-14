/** 
 * ???? ???? ? ???
 * @param time
 * @return
 */
public static int[] getDateDetail(long time){
  final Calendar mCalendar=Calendar.getInstance();
  mCalendar.setTimeInMillis(time);
  return new int[]{mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH) + 1,mCalendar.get(Calendar.DAY_OF_MONTH)};
}
