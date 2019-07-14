/** 
 * @return Numeric version as String
 */
public String getVersion(){
  if (this.equals(EMPTY))   return null;
  if (this.equals(LATEST))   return Long.toString(Long.MAX_VALUE);
  return displayText;
}
