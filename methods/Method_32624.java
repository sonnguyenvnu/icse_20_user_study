/** 
 * Creates a new Period instance with the same field values but different PeriodType. <p> This period instance is immutable and unaffected by this method call.
 * @param type  the period type to use, null means standard
 * @return the new period instance
 * @throws IllegalArgumentException if the new period won't accept all of the current fields
 */
public Period withPeriodType(PeriodType type){
  type=DateTimeUtils.getPeriodType(type);
  if (type.equals(getPeriodType())) {
    return this;
  }
  return new Period(this,type);
}
