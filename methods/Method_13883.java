static boolean isFusionTableURL(URL url){
  String query=url.getQuery();
  if (query == null) {
    query="";
  }
  return url.getHost().endsWith(".google.com") && url.getPath().startsWith("/fusiontables/DataSource") && (query.contains("dsrcid=") || query.contains("docid="));
}
