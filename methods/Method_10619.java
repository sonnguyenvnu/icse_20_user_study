/** 
 * ?????? yyyyMMddHHmmss
 * @return String
 */
public static String getCurrTime(){
  Date now=new Date();
  SimpleDateFormat outFormat=new SimpleDateFormat("yyyyMMddHHmmss");
  String s=outFormat.format(now);
  return s;
}
