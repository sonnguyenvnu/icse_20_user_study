/** 
 * Set a time zone to be used when computeMillis is called.
 */
public void setZone(DateTimeZone zone){
  iSavedState=null;
  iZone=zone;
}
