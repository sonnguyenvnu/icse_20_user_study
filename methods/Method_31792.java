/** 
 * Output the instant using the specified format pattern.
 * @param pattern  the pattern specification, null means use <code>toString</code>
 * @param locale  Locale to use, null means default
 * @return the formatted string, not null
 * @see org.joda.time.format.DateTimeFormat
 */
public String toString(String pattern,Locale locale) throws IllegalArgumentException {
  if (pattern == null) {
    return toString();
  }
  return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
}
