/** 
 * Get the maximum length of the abbreviated text returned by this field.
 * @param locale  the locale to use
 * @return the maximum abbreviated textual length
 */
public int getMaximumShortTextLength(Locale locale){
  return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxShortTextLength();
}
