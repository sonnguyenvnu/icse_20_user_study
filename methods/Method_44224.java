/** 
 * @param unixSeconds
 * @return
 */
public static String unixTimeToDate(long unixSeconds){
  if (unixSeconds > 0) {
    Date date=new java.util.Date(unixSeconds * 1000L);
    SimpleDateFormat dateFormat=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(date);
  }
 else {
    return "";
  }
}
