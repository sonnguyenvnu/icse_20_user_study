public static String inputPassToFormPass(String inputPass){
  String str="" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
  System.out.println(str);
  return md5(str);
}
