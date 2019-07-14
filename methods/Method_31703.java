/** 
 * Formats the time of this date in the standard ISO HH:mm:ss format.
 * @param date The date to format.
 * @return The time in ISO format. An empty string if the time is null.
 */
public static String formatTimeAsIsoString(Date date){
  if (date == null) {
    return "";
  }
  return new SimpleDateFormat("HH:mm:ss").format(date);
}
