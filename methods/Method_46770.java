private static String preserveSubpackageName(final String baseUrlString,final Resource resource,final String rootPath){
  try {
    return rootPath + (rootPath.endsWith("/") ? "" : "/") + resource.getURL().toString().substring(baseUrlString.length());
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
}
