/** 
 * Returns the offset constant to this variable. 
 */
public static String offsetName(String varName){
  return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,varName) + "_OFFSET";
}
