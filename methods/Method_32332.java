/** 
 * Appends just a parser element which is optional. With no matching printer, a printer cannot be built from this DateTimeFormatterBuilder. <p> The parser interface is part of the low-level part of the formatting API. Normally, instances are extracted from another formatter. Note however that any formatter specific information, such as the locale, time-zone, chronology, offset parsing or pivot/default year, will not be extracted by this method.
 * @return this DateTimeFormatterBuilder, for chaining
 * @throws IllegalArgumentException if parser is null or of an invalid type
 */
public DateTimeFormatterBuilder appendOptional(DateTimeParser parser){
  checkParser(parser);
  InternalParser[] parsers=new InternalParser[]{DateTimeParserInternalParser.of(parser),null};
  return append0(null,new MatchingParser(parsers));
}
