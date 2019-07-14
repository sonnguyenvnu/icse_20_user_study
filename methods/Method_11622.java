public static String removeProtocol(String url){
  return patternForProtocal.matcher(url).replaceAll("");
}
