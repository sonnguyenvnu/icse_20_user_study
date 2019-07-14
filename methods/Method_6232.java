public static boolean isPassportUrl(String url){
  if (url == null) {
    return false;
  }
  try {
    url=url.toLowerCase();
    if (url.startsWith("tg:passport") || url.startsWith("tg://passport") || url.startsWith("tg:secureid") || url.contains("resolve") && url.contains("domain=telegrampassport")) {
      return true;
    }
  }
 catch (  Throwable ignore) {
  }
  return false;
}
