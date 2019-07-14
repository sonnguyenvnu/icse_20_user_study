/** 
 * ??? ?? yyyy-MM-dd HH:mm:ss
 * @param time
 * @return
 */
public static Date getDate(String time){
  try {
    return dateFormat.parse(time);
  }
 catch (  ParseException e) {
    e.printStackTrace();
  }
  return new Date();
}
