@NonNull public static String getEndpoint(@NonNull String url){
  if (url.startsWith("http://")) {
    url=url.replace("http://","https://");
  }
  if (!url.startsWith("https://")) {
    url="https://" + url;
  }
  return getEnterpriseUrl(url);
}
