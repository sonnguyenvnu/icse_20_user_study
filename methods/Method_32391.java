/** 
 * Returns a formatter for a two digit hour of day, two digit minute of hour, two digit second of minute, three digit fraction of second, and time zone offset (HH:mm:ss.SSSZZ). <p> The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero. The parser is strict by default, thus time string  {@code 24:00} cannot be parsed.<p> The returned formatter prints and parses only this format, which includes milliseconds. See  {@link #timeParser()} for a more flexible parser that accepts different formats.
 * @return a formatter for HH:mm:ss.SSSZZ
 */
public static DateTimeFormatter time(){
  return Constants.t;
}
