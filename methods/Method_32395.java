/** 
 * Returns a formatter for a four digit weekyear, two digit week of weekyear, and one digit day of week. (xxxx-'W'ww-e)
 * @return a formatter for xxxx-'W'ww-e
 */
public static DateTimeFormatter weekyearWeekDay(){
  return Constants.wwd;
}
