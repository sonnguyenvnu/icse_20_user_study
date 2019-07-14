/** 
 * Replaces bundle marker with calculated bundle id. Used for <code>RESOURCE_ONLY</code> strategy.
 */
public char[] replaceBundleId(final char[] content){
  if (strategy == ACTION_MANAGED || bundleId == null) {
    return content;
  }
  int index=ArraysUtil.indexOf(content,bundleIdMark);
  if (index == -1) {
    return content;
  }
  char[] bundleIdChars=bundleId.toCharArray();
  char[] result=new char[content.length - bundleIdMark.length + bundleIdChars.length];
  System.arraycopy(content,0,result,0,index);
  System.arraycopy(bundleIdChars,0,result,index,bundleIdChars.length);
  System.arraycopy(content,index + bundleIdMark.length,result,index + bundleIdChars.length,content.length - bundleIdMark.length - index);
  return result;
}
