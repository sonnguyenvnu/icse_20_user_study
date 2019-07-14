/** 
 * ?????
 * @param dateStr
 * @return
 */
public static Date getDateByStr(String dateStr){
  SimpleDateFormat formatter=null;
  if (dateStr == null) {
    return null;
  }
 else   if (dateStr.length() == 10) {
    formatter=new SimpleDateFormat("yyyy-MM-dd");
  }
 else   if (dateStr.length() == 16) {
    formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
  }
 else   if (dateStr.length() == 19) {
    formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  }
 else   if (dateStr.length() > 19) {
    dateStr=dateStr.substring(0,19);
    formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  }
 else {
    return null;
  }
  try {
    return formatter.parse(dateStr);
  }
 catch (  ParseException e) {
    logger.error(e);
    return null;
  }
}
