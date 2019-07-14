/** 
 * ????????
 * @param startDate
 * @param endDate
 * @return
 */
public static long getDateDiff(String startDate,String endDate){
  long diff=0;
  try {
    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
    Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
    diff=(date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) : (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
  }
 catch (  ParseException e) {
  }
  return diff;
}
