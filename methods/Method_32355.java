/** 
 * Calls upon  {@link DateTimeFormat} to parse the pattern and append theresults into this builder.
 * @param pattern  pattern specification
 * @throws IllegalArgumentException if the pattern is invalid
 * @see DateTimeFormat
 */
public DateTimeFormatterBuilder appendPattern(String pattern){
  DateTimeFormat.appendPatternTo(this,pattern);
  return this;
}
