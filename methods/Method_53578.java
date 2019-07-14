/** 
 * ?????10??
 * @param data
 * @return
 */
public static boolean isDecimal(String data){
  if (data.startsWith("0") || data.startsWith("0x") || data.startsWith("0X")) {
    System.out.println(data + "??10???");
    return false;
  }
  String regex="[0-9]+$";
  if (data.matches(regex)) {
    System.out.println(data + "?10???");
    return true;
  }
 else {
    System.out.println(data + "??10???");
    return false;
  }
}
