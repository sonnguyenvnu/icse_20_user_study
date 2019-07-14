/** 
 * Get the maximum length of the text returned by this field.
 * @param locale  the locale to use
 * @return the maximum textual length
 */
public int getMaximumTextLength(Locale locale){
  return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxTextLength();
}
