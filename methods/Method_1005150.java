/** 
 * ?????
 * @param date
 * @param format
 * @return
 */
public static String dateformat(String date,String format){
  SimpleDateFormat sformat=new SimpleDateFormat(format);
  Date _date=null;
  try {
    _date=sformat.parse(date);
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return sformat.format(_date);
}
