/** 
 * ??????double ,?????? return 0;
 * @param str ???
 * @return
 */
public static double stringToDouble(String str){
  if (isNullString(str)) {
    return 0;
  }
 else {
    try {
      return Double.parseDouble(str);
    }
 catch (    NumberFormatException e) {
      return 0;
    }
  }
}
