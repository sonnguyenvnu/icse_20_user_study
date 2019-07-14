/** 
 * ???? ???? ?? ?? ?? ? ???
 * @param time
 * @return
 */
public static int[] getWholeDetail(long time){
  final Calendar mCalendar=Calendar.getInstance();
  mCalendar.setTimeInMillis(time);
  return new int[]{mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH) + 1,mCalendar.get(Calendar.DAY_OF_MONTH),mCalendar.get(Calendar.HOUR_OF_DAY),mCalendar.get(Calendar.MINUTE),mCalendar.get(Calendar.SECOND)};
}
