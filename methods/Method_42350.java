/** 
 * ??????????
 * @param dayNum
 * @return
 */
public static String getDateByDayNum(int dayNum){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  Calendar cal=Calendar.getInstance();
  cal.setTime(new Date());
  cal.add(Calendar.DAY_OF_MONTH,-dayNum);
  String result=sdf.format(cal.getTime());
  return result;
}
