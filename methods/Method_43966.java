/** 
 * Converts a date to a UTC String representation
 * @param date
 * @return the formatted date
 */
public static String toUTCString(Date date){
  SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
  sd.setTimeZone(TimeZone.getTimeZone("GMT"));
  return sd.format(date);
}
