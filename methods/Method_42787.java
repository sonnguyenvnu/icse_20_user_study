/** 
 * ??????????????????
 * @param str ???????
 * @param minLength ????
 */
public static boolean checkStrMaxLengthByBytes(String str,Integer maxLength){
  int length=str.getBytes().length;
  if (length <= maxLength) {
    return true;
  }
 else {
    return false;
  }
}