/** 
 * check if orginalStr is null or empty
 * @param String ... originalStrArray
 * @return true if have one blank at least.
 */
public static boolean isBlank(String... originalStrArray){
  if (null == originalStrArray || 0 == originalStrArray.length)   return true;
  for (int i=0; i < originalStrArray.length; i++) {
    if (isBlank(originalStrArray[i]))     return true;
  }
  return false;
}
