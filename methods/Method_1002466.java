/** 
 * Returns true if interpretation of any instruction failed.
 * @return true if interpretation of any instruction failed
 */
public boolean failed(){
  return !_errorMessages.isEmpty();
}
