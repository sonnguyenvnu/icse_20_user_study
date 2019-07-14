public static boolean wehcatValidation(String str,String token){
  boolean flag=false;
  if (wechatEncode(str).equals(token)) {
    flag=true;
  }
  return flag;
}
