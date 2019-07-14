/** 
 * Get this object as a DateTime in the same zone.
 * @return a DateTime using the same millis
 */
public DateTime toDateTime(){
  return new DateTime(getMillis(),getZone());
}
