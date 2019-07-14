/** 
 * ?????16??
 * @param data
 * @return
 */
public static boolean isHexadecimal(String data){
  String regex="^[A-Fa-f0-9]+$";
  if (data.matches(regex)) {
    System.out.println(data + "?16???");
    return true;
  }
 else {
    System.out.println(data + "??16???");
    return false;
  }
}
