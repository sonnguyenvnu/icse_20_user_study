private static URL resourceModuleUrl(ClassLoader classLoader,String resourceClassPath){
  URL url=classLoader.getResource(resourceClassPath);
  if (url == null) {
    return null;
  }
  String urlString=url.toString();
  int ndx=urlString.indexOf(resourceClassPath);
  urlString=urlString.substring(0,ndx) + urlString.substring(ndx + resourceClassPath.length());
  try {
    return new URL(urlString);
  }
 catch (  MalformedURLException ignore) {
    return null;
  }
}
