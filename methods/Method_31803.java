/** 
 * Get this object as a MutableDateTime in the same zone.
 * @return a MutableDateTime using the same millis
 */
public MutableDateTime toMutableDateTime(){
  return new MutableDateTime(getMillis(),getZone());
}
