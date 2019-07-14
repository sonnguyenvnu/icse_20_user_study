/** 
 * ????date
 * @param dateString
 * @return
 */
public static Date StringToDate(String dateString){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
  Date date=null;
  try {
    date=sdf.parse(dateString);
  }
 catch (  ParseException e) {
    logger.error(e);
  }
  return date;
}
