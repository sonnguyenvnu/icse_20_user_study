/** 
 * ????????? Float
 * @param str ???????
 * @return ???? float
 */
public static float stringToFloat(String str){
  if (isNullString(str)) {
    return 0;
  }
 else {
    try {
      return Float.parseFloat(str);
    }
 catch (    NumberFormatException e) {
      return 0;
    }
  }
}
