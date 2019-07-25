public static String get(Controller ctr,String key){
  String encrypt_key=COOKIE_ENCRYPT_KEY;
  String cookieValue=ctr.getCookie(key);
  if (cookieValue == null) {
    return null;
  }
  String value=new String(Base64Kit.decode(cookieValue));
  return getFromCookieInfo(encrypt_key,value);
}
