public static void put(Controller ctr,String key,String value,int maxAgeInSeconds,String domain){
  String cookie=buildCookieValue(value,maxAgeInSeconds);
  ctr.setCookie(key,cookie,maxAgeInSeconds,null,domain,false);
}
