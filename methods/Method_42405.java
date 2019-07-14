/** 
 * ??????????????2013/12/09 00:00:00 ????
 */
public static Date subDays(int days){
  Date date=addDay(new Date(),-days);
  String dateStr=getReqDate(date);
  Date date1=null;
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  try {
    date1=sdf.parse(dateStr);
  }
 catch (  ParseException e) {
    logger.error(e);
  }
  return date1;
}
