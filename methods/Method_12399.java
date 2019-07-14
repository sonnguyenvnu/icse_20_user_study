/** 
 * Checks the syntax of the given URL.
 * @param url The URL.
 * @return true, if valid.
 */
private boolean checkUrl(String url){
  try {
    URI uri=new URI(url);
    return uri.isAbsolute();
  }
 catch (  URISyntaxException e) {
    return false;
  }
}
