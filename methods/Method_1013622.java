public static boolean excludes(String url){
  if (stopsso) {
    return true;
  }
  return Pattern.matches(SsoConfig.excludeRegex,url);
}
