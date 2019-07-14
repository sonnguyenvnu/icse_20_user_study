/** 
 * Creates a format that outputs a full datetime format. <p> The format will change as you change the locale of the formatter. Call  {@link DateTimeFormatter#withLocale(Locale)} to switch the locale.
 * @return the formatter
 */
public static DateTimeFormatter fullDateTime(){
  return createFormatterForStyleIndex(FULL,FULL);
}
