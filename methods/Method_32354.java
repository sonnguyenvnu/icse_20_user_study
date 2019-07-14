/** 
 * Instructs the printer to emit a locale-specific time zone name. Using this method prevents parsing, because time zone names are not unique. See  {@link #appendTimeZoneName(Map)}.
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendTimeZoneName(){
  return append0(new TimeZoneName(TimeZoneName.LONG_NAME,null),null);
}
