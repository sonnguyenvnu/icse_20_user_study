/** 
 * Check String is equals Ignore Case. targetStr compare with compareStrArray, and return true if equals all
 * @param targetStr ???????????
 * @param compareStrArray ?????????????????????
 * @return true if targetStr same with every string in compareStrArray
 */
public static boolean equalsIgnoreCaseAll(String targetStr,String... compareStrArray){
  if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length) {
    return false;
  }
  for (int i=0; i < compareStrArray.length; i++) {
    if (!targetStr.equalsIgnoreCase(compareStrArray[i])) {
      return false;
    }
  }
  return true;
}
