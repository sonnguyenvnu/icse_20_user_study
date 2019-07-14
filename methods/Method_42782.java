/** 
 * ????????hh:mm:ss
 * @param timeStr
 * @return
 */
public static boolean isTime(String timeStr){
  SimpleDateFormat df=new SimpleDateFormat("hh:mm:ss");
  Date date=null;
  try {
    date=df.parse(timeStr);
  }
 catch (  ParseException e) {
    return false;
  }
  return date != null;
}
