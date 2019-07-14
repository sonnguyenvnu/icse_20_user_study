/** 
 * ????????
 * @param singlePinyin
 * @return
 */
public static boolean valid(String singlePinyin){
  if (mapNumberKey.containsKey(singlePinyin))   return true;
  return false;
}
