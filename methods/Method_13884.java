static private String getParamValue(URL url,String key){
  String query=url.getQuery();
  if (query != null) {
    String[] parts=query.split("&");
    for (    String part : parts) {
      if (part.startsWith(key + "=")) {
        int offset=key.length() + 1;
        String tableId=part.substring(offset);
        return tableId;
      }
    }
  }
  return null;
}
