/** 
 * ?????2??
 * @param data
 * @return
 */
public static boolean isBinary(String data){
  String regex="[0-1]+$";
  if (data.matches(regex)) {
    System.out.println(data.toUpperCase() + "?2???");
    return true;
  }
 else {
    System.out.println(data.toUpperCase() + "??2???");
    return false;
  }
}
