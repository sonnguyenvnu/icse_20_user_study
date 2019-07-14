/** 
 * ??? ?? yyyy-MM-dd HH:mm:ss
 * @param date
 * @return
 */
public static String getTimeStamp(Date date){
  if (date == null) {
    return dateFormat.format(new Date());
  }
 else {
    return dateFormat.format(date);
  }
}
