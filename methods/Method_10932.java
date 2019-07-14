/** 
 * ?????? ?? ????? ??? DATE ????? return null;
 * @param format
 * @param datess
 * @return
 */
public static Date string2Date(String format,String datess){
  SimpleDateFormat sdr=new SimpleDateFormat(format);
  Date date=null;
  try {
    date=sdr.parse(datess);
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return date;
}
