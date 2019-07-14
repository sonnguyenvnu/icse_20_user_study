/** 
 * Changes return type.
 */
protected static String changeReturnType(final String desc,final String type){
  int ndx=desc.indexOf(')');
  return desc.substring(0,ndx + 1) + type;
}
