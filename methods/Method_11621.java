public static String fixIllegalCharacterInUrl(String url){
  return url.replace(" ","%20").replaceAll("#+","#");
}
