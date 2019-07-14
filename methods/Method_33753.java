/** 
 * ??????????12?30
 */
public static boolean isRightTime(){
  Time t=new Time();
  t.setToNow();
  int hour=t.hour;
  int minute=t.minute;
  return hour > 12 || (hour == 12 && minute >= 30);
}
