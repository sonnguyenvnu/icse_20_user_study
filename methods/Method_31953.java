/** 
 * Serialization singleton.
 */
private Object readResolve(){
  Chronology base=getBase();
  return base == null ? getInstance(DateTimeZone.UTC,getMinimumDaysInFirstWeek()) : getInstance(base.getZone(),getMinimumDaysInFirstWeek());
}
