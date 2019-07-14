/** 
 * current date in utc as http header 
 */
protected static String utcNow(){
  Calendar calendar=Calendar.getInstance();
  SimpleDateFormat dateFormat=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.US);
  dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
  return dateFormat.format(calendar.getTime());
}
