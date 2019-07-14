/** 
 * Appends argument to the existing description.
 */
protected static String appendArgument(final String desc,final String type){
  int ndx=desc.indexOf(')');
  return desc.substring(0,ndx) + type + desc.substring(ndx);
}
