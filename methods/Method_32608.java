/** 
 * Output the date using the specified format pattern. Unsupported fields will appear as special unicode characters.
 * @param pattern  the pattern specification, null means use <code>toString</code>
 * @param locale  Locale to use, null means default
 * @see org.joda.time.format.DateTimeFormat
 */
public String toString(String pattern,Locale locale){
  if (pattern == null) {
    return toString();
  }
  return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
}
