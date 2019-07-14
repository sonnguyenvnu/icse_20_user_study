/** 
 * ????????
 * @param date
 * @return
 */
public static Date dateFormat(Date date){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  Date value=new Date();
  try {
    value=sdf.parse(sdf.format(date));
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return value;
}
