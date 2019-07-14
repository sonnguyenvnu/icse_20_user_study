/** 
 * Set the millisecond value.
 * @param val millisecond value
 * @throws CalendarParserException if the value is not a valid millisecond
 */
void setMillisecond(int val) throws CalendarParserException {
  if (val < 0 || val > 999) {
    throw new CalendarParserException("Bad millisecond " + val);
  }
  milli=val;
}
