private static URL classModuleUrl(final ClassLoader classLoader,final Class clazz){
  if (clazz == null) {
    return null;
  }
  final String name=clazz.getName().replace('.','/') + ".class";
  final URL url=classLoader.getResource(name);
  if (url == null) {
    return null;
  }
  String urlString=url.toString();
  final int ndx=urlString.indexOf(name);
  urlString=urlString.substring(0,ndx) + urlString.substring(ndx + name.length());
  try {
    return new URL(urlString);
  }
 catch (  MalformedURLException ignore) {
    return null;
  }
}
