/** 
 * Appends method name to existing path.
 */
protected void append(final String methodName){
  if (path.length() != 0) {
    path+=StringPool.DOT;
  }
  if (methodName.startsWith(StringPool.LEFT_SQ_BRACKET)) {
    path=StringUtil.substring(path,0,-1);
  }
  path+=methodName;
}
