/** 
 * Returns the pattern used by a particular style and locale. <p> The first character is the date style, and the second character is the time style. Specify a character of 'S' for short style, 'M' for medium, 'L' for long, and 'F' for full. A date or time may be omitted by specifying a style character '-'.
 * @param style  two characters from the set {"S", "M", "L", "F", "-"}
 * @param locale  locale to use, null means default
 * @return the formatter
 * @throws IllegalArgumentException if the style is invalid
 * @since 1.3
 */
public static String patternForStyle(String style,Locale locale){
  DateTimeFormatter formatter=createFormatterForStyle(style);
  if (locale == null) {
    locale=Locale.getDefault();
  }
  return ((StyleFormatter)formatter.getPrinter0()).getPattern(locale);
}
