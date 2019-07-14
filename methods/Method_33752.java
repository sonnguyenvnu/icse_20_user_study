/** 
 * ??????
 */
public static String getData(){
  SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
  String date=sDateFormat.format(new Date());
  return date;
}
