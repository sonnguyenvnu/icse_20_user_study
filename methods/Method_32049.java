/** 
 * Selects a suitable period type for the given object.
 * @param object  the object to examine
 * @return the period type, never null
 */
public PeriodType getPeriodType(Object object){
  return PeriodType.standard();
}
