/** 
 * Get the datetime zone as a  {@link java.util.TimeZone}.
 * @return the closest matching TimeZone object
 */
public java.util.TimeZone toTimeZone(){
  return java.util.TimeZone.getTimeZone(iID);
}
