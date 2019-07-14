/** 
 * Check String is equals. targetStr compare with compareStrArray, and return true if equals one or more
 * @param targetStr ???????????
 * @param compareStrArray ?????????????????????
 * @return
 */
public static boolean containsIgnoreCase(final String originalStr,final CharSequence targetStr){
  if (null == originalStr) {
    return false;
  }
  String originalStrCaps=originalStr.toUpperCase();
  String targetStrCaps=targetStr.toString().toUpperCase();
  return originalStrCaps.contains(targetStrCaps);
}
