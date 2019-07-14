/** 
 * Instructs the printer to emit a locale-specific era text (BC/AD), and the parser to expect it. The parser is case-insensitive.
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendEraText(){
  return appendText(DateTimeFieldType.era());
}
