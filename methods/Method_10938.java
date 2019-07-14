/** 
 * "mm:ss" ??? ????
 * @param time
 * @return
 */
public static long formatSeconds(String time){
  String format=RxConstants.DATE_FORMAT_MM_SS;
  SimpleDateFormat sdf=new SimpleDateFormat(format);
  sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
  Date date;
  long times=0;
  try {
    date=sdf.parse(time);
    long l=date.getTime();
    times=l;
    Log.d("???",times + "");
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return times;
}
