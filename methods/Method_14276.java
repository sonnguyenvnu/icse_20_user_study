/** 
 * Set the day of month value.
 * @param val day of month value
 * @throws CalendarParserException if the value is not a valid day of month
 */
void setDate(int val) throws CalendarParserException {
  if (val < 1 || val > 31) {
    throw new CalendarParserException("Bad day " + val);
  }
  day=val;
}
