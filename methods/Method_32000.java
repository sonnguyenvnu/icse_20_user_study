/** 
 * Serialization singleton
 */
private Object readResolve(){
  Chronology base=getBase();
  int minDays=getMinimumDaysInFirstWeek();
  minDays=(minDays == 0 ? 4 : minDays);
  return base == null ? getInstance(DateTimeZone.UTC,minDays) : getInstance(base.getZone(),minDays);
}
