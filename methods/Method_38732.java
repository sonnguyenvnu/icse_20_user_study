/** 
 * Resolves JSON name from real name.
 */
public String resolveJsonName(final String realName){
  if (realNames == null) {
    return realName;
  }
  int realIndex=ArraysUtil.indexOf(realNames,realName);
  if (realIndex == -1) {
    return realName;
  }
  return jsonNames[realIndex];
}
