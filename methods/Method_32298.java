/** 
 * Creates a format that outputs a short date format. <p> The format will change as you change the locale of the formatter. Call  {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
 * @return the formatter
 */
public static DateTimeFormatter shortDate(){
  return createFormatterForStyleIndex(SHORT,NONE);
}
