/** 
 * Returns whether this  {@link OpenLocationCode} is a short Open Location Code.
 * @return True if it is short.
 */
public boolean isShort(){
  return code.indexOf(SEPARATOR) >= 0 && code.indexOf(SEPARATOR) < SEPARATOR_POSITION;
}
