/** 
 * Returns a new formatter with a different PeriodType for parsing. <p> A PeriodFormatter is immutable, so a new instance is returned, and the original is unaltered and still usable.
 * @param type  the type to use in parsing
 * @return the new formatter
 */
public PeriodFormatter withParseType(PeriodType type){
  if (type == iParseType) {
    return this;
  }
  return new PeriodFormatter(iPrinter,iParser,iLocale,type);
}
