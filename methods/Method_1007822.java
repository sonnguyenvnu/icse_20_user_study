/** 
 * URL may contain spaces and other nasties that will cause a failure.
 * @param existing the existing URL to transform
 * @return the new URL, or old one if there was a failure
 */
private static URL reformat(URL existing){
  try {
    URL url=new URL(existing.toString());
    URI uri=new URI(url.getProtocol(),url.getUserInfo(),url.getHost(),url.getPort(),url.getPath(),url.getQuery(),url.getRef());
    url=uri.toURL();
    return url;
  }
 catch (  MalformedURLException e) {
    return existing;
  }
catch (  URISyntaxException e) {
    return existing;
  }
}
