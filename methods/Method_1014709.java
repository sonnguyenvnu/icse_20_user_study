/** 
 * ?????????????
 * @param strDate ?????
 * @param pattern ????
 * @return
 */
public static Date parse(String strDate,String pattern){
  SimpleDateFormat df=new SimpleDateFormat(pattern);
  try {
    return df.parse(strDate);
  }
 catch (  ParseException e) {
    e.printStackTrace();
    return null;
  }
}
