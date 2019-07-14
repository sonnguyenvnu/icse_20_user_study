/** 
 * Serialization singleton
 */
private Object readResolve(){
  Chronology base=getBase();
  return base == null ? getInstanceUTC() : getInstance(base.getZone());
}
