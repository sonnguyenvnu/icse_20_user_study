/** 
 * Set the second value.
 * @param val second value
 * @throws CalendarParserException if the value is not a valid second
 */
void setSecond(int val) throws CalendarParserException {
  if (val < 0 || val > 59) {
    throw new CalendarParserException("Bad second " + val);
  }
  second=val;
}
