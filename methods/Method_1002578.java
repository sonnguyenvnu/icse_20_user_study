/** 
 * Check backwards compatibility between two idl (.restspec.json) files.
 * @param prevRestspecPath previously existing idl file
 * @param currRestspecPath current idl file
 * @return true if the previous idl file is equivalent to the current one
 */
public boolean check(String prevRestspecPath,String currRestspecPath){
  return check(prevRestspecPath,currRestspecPath,CompatibilityLevel.EQUIVALENT);
}
