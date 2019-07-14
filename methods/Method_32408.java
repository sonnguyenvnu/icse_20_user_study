/** 
 * Returns a new formatter with a different locale that will be used for printing and parsing. <p> A PeriodFormatter is immutable, so a new instance is returned, and the original is unaltered and still usable. <p> A null locale indicates that no specific locale override is in use.
 * @param locale  the locale to use
 * @return the new formatter
 */
public PeriodFormatter withLocale(Locale locale){
  if (locale == getLocale() || (locale != null && locale.equals(getLocale()))) {
    return this;
  }
  return new PeriodFormatter(iPrinter,iParser,locale,iParseType);
}
