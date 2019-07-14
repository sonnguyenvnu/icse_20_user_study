/** 
 * ????????????
 */
public static boolean DateCompare(String s1) throws ParseException {
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  Date d1=sdf.parse(s1);
  Date d2=sdf.parse(getData());
  if (((d1.getTime() - d2.getTime()) / (24 * 3600 * 1000)) >= 1) {
    return true;
  }
 else {
    return false;
  }
}
