public static String slurp(String resource){
  try {
    URL url=Resources.class.getResource(resource);
    if (url == null) {
      throw new FileNotFoundException(resource);
    }
    return slurp(url.openStream());
  }
 catch (  IOException ex) {
    throw new IllegalStateException(ex);
  }
}
