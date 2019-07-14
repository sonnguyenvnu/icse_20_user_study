/** 
 * Returns a formatter that combines a full date and time, separated by a 'T' (yyyy-MM-dd'T'HH:mm:ss.SSSZZ). <p> The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero. The parser is strict by default, thus time string  {@code 24:00} cannot be parsed.<p> The returned formatter prints and parses only this format, which includes milliseconds. See  {@link #dateTimeParser()} for a more flexible parser that accepts different formats.
 * @return a formatter for yyyy-MM-dd'T'HH:mm:ss.SSSZZ
 */
public static DateTimeFormatter dateTime(){
  return Constants.dt;
}
