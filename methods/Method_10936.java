/** 
 * ???????
 * @param format ???? yyyy-MM-dd HH:mm:ss
 * @return
 */
public static String getYestoryDate(String format){
  Calendar calendar=Calendar.getInstance();
  calendar.add(Calendar.DATE,-1);
  return simpleDateFormat(format,calendar.getTime());
}
