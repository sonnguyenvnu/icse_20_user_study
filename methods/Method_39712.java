/** 
 * Prepends argument to the existing description.
 */
protected static String prependArgument(final String desc,final String type){
  int ndx=desc.indexOf('(');
  ndx++;
  return desc.substring(0,ndx) + type + desc.substring(ndx);
}
