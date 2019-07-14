/** 
 * Returns a formatter for a full date as four digit weekyear, two digit week of weekyear, and one digit day of week (xxxx-'W'ww-e). <p> The returned formatter prints and parses only this format. See  {@link #dateParser()} for a more flexible parser that accepts different formats.
 * @return a formatter for xxxx-'W'ww-e
 */
public static DateTimeFormatter weekDate(){
  return Constants.wwd;
}
