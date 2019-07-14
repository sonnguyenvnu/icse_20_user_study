private static URL fixManifestUrl(URL url){
  String urlString=url.toString();
  int ndx=urlString.indexOf(MANIFEST);
  urlString=urlString.substring(0,ndx) + urlString.substring(ndx + MANIFEST.length());
  try {
    return new URL(urlString);
  }
 catch (  MalformedURLException ignore) {
    return null;
  }
}
