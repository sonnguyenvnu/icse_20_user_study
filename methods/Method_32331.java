/** 
 * Appends a printer/parser pair. <p> The printer and parser interfaces are the low-level part of the formatting API. Normally, instances are extracted from another formatter. Note however that any formatter specific information, such as the locale, time-zone, chronology, offset parsing or pivot/default year, will not be extracted by this method.
 * @param printer  the printer to add, not null
 * @param parser  the parser to add, not null
 * @return this DateTimeFormatterBuilder, for chaining
 * @throws IllegalArgumentException if printer or parser is null or of an invalid type
 */
public DateTimeFormatterBuilder append(DateTimePrinter printer,DateTimeParser parser){
  checkPrinter(printer);
  checkParser(parser);
  return append0(DateTimePrinterInternalPrinter.of(printer),DateTimeParserInternalParser.of(parser));
}
