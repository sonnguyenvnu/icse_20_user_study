/** 
 * Override to return the correct timezone instance.
 * @since 1.5
 */
public java.util.TimeZone toTimeZone(){
  String id=getID();
  if (id.length() == 6 && (id.startsWith("+") || id.startsWith("-"))) {
    return java.util.TimeZone.getTimeZone("GMT" + getID());
  }
  return new java.util.SimpleTimeZone(iWallOffset,getID());
}
