private static boolean isValidUrl(String name){
  if (isHttpUrl(name)) {
    String url=StringUtils.strip(name);
    try {
      HttpURLConnection connection=(HttpURLConnection)new URL(url).openConnection();
      connection.setRequestMethod("HEAD");
      connection.setConnectTimeout(ResourceLoader.TIMEOUT);
      connection.setReadTimeout(ResourceLoader.TIMEOUT);
      int responseCode=connection.getResponseCode();
      if (responseCode == 200) {
        return true;
      }
    }
 catch (    IOException e) {
      return false;
    }
  }
  return false;
}
