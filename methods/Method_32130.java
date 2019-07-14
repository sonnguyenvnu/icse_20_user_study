/** 
 * Gets the period type handling null. <p> If the zone is <code>null</code>,  {@link PeriodType#standard()}will be returned. Otherwise, the type specified is returned.
 * @param type  the time zone to use, null means the standard type
 * @return the type to use, never null
 */
public static final PeriodType getPeriodType(PeriodType type){
  if (type == null) {
    return PeriodType.standard();
  }
  return type;
}
