/** 
 * Returns true if this qualified name identifies an anonymous class.
 */
public boolean isAnonymousClass(){
  return !isLocalClass() && StringUtils.isNumeric(getClassSimpleName());
}
