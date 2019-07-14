/** 
 * Returns whether this  {@link OpenLocationCode} is a full Open Location Code.
 * @return True if it is a full code.
 */
public boolean isFull(){
  return code.indexOf(SEPARATOR) == SEPARATOR_POSITION;
}
