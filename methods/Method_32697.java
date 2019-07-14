/** 
 * Sets the standard offset to use for newly added rules until the next cutover is added.
 * @param standardOffset  the standard offset in millis
 */
public DateTimeZoneBuilder setStandardOffset(int standardOffset){
  getLastRuleSet().setStandardOffset(standardOffset);
  return this;
}
