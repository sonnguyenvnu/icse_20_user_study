/** 
 * Instructs the printer to emit a short locale-specific dayOfWeek text. The parser will accept a long or short dayOfWeek text, case-insensitive.
 * @return this DateTimeFormatterBuilder, for chaining
 */
public DateTimeFormatterBuilder appendDayOfWeekShortText(){
  return appendShortText(DateTimeFieldType.dayOfWeek());
}
