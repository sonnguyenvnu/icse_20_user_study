/** 
 * Returns a new formatter with a different locale that will be used for printing and parsing. <p> A DateTimeFormatter is immutable, so a new instance is returned, and the original is unaltered and still usable.
 * @param locale the locale to use; if null, formatter uses default localeat invocation time
 * @return the new formatter
 */
public DateTimeFormatter withLocale(Locale locale){
  if (locale == getLocale() || (locale != null && locale.equals(getLocale()))) {
    return this;
  }
  return new DateTimeFormatter(iPrinter,iParser,locale,iOffsetParsed,iChrono,iZone,iPivotYear,iDefaultYear);
}
