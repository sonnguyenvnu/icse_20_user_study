public static void put(Controller ctr,String key,String value,int maxAgeInSeconds){
  String cookie=buildCookieValue(value,maxAgeInSeconds);
  ctr.setCookie(key,cookie,maxAgeInSeconds);
}
