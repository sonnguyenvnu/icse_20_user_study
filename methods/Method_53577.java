/** 
 * ?????8??
 * @param data
 * @return
 */
public static boolean isOctal(String data){
  String substring;
  String regex="[0-7]+$";
  if (data.startsWith("0")) {
    substring=data.substring(1);
    if (substring.matches(regex)) {
      System.out.println(data + "?8???");
      return true;
    }
 else {
      System.out.println(data + "??8???");
      return false;
    }
  }
 else {
    return false;
  }
}
