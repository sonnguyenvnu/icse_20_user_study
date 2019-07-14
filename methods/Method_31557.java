/** 
 * Filter out parameters to avoid including passwords, etc.
 * @param url The raw url.
 * @return The filtered url.
 */
static String filterUrl(String url){
  int questionMark=url.indexOf("?");
  if (questionMark >= 0 && !url.contains("?databaseName=")) {
    url=url.substring(0,questionMark);
  }
  url=url.replaceAll("://.*:.*@","://");
  return url;
}
