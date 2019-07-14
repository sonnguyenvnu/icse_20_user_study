private static void addClasspathURLs(final List<URL> urls,final String classpath) throws MalformedURLException {
  StringTokenizer toker=new StringTokenizer(classpath,File.pathSeparator);
  while (toker.hasMoreTokens()) {
    String token=toker.nextToken();
    LOG.log(Level.FINE,"Adding classpath entry: <{0}>",token);
    urls.add(createURLFromPath(token));
  }
}
