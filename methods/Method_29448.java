/** 
 * Check String is equals. targetStr compare with compareStrArray, and return true if equals one or more
 * @param targetStr ???????????
 * @param compareStrArray ?????????????????????
 * @return true if targetStr same with string in compareStrArray one atleast
 */
public static boolean equalsIgnoreCaseOne(String targetStr,String... compareStrArray){
  if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length) {
    return false;
  }
  for (int i=0; i < compareStrArray.length; i++) {
    if (targetStr.equalsIgnoreCase(compareStrArray[i])) {
      return true;
    }
  }
  return false;
}
