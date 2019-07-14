/** 
 * ?????????????201406300000
 * @param date  ????
 * @param offset ?????????
 * @return ???long????201406300000
 */
public static long getBeginTimeOfDay(Date date,int offset){
  Calendar calendar=Calendar.getInstance();
  calendar.setTime(date);
  calendar.add(Calendar.DAY_OF_YEAR,offset);
  date=calendar.getTime();
  SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT);
  return Long.valueOf(dateFormat.format(date) + "0000");
}
