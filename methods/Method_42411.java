/** 
 * ??????????
 * @return Date
 */
public static String getYesterday(){
  Calendar calendar=Calendar.getInstance();
  calendar.add(Calendar.DATE,-1);
  return formatDate(calendar.getTime(),"yyyy-MM-dd");
}
