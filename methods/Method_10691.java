/** 
 * ???????? ,?????? return 0;
 * @param str ???
 * @return
 */
public static int stringToInt(String str){
  if (isNullString(str)) {
    return 0;
  }
 else {
    try {
      return Integer.parseInt(str);
    }
 catch (    NumberFormatException e) {
      return 0;
    }
  }
}
