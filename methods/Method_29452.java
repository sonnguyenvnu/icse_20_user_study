/** 
 * check if orginalStr is null or empty. <br> If have more than one originalStr, use isBlank(String... originalStrArray)
 * @param originalStr ??‘???
 * @return true or false;
 */
public static boolean isBlank(String originalStr){
  if (null == originalStr) {
    return true;
  }
  if (originalStr.contains(BaseConstant.WORD_SEPARATOR)) {
    return false;
  }
  return trimToEmpty(originalStr).isEmpty();
}
