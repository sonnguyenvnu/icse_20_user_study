/** 
 * ????,hh:mm:ss
 * @param date
 * @return
 */
public static String getTime(long date){
  return new SimpleDateFormat("hh:mm:ss").format(new Date(date));
}
