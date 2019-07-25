public static InputStream open(String resource,ClassLoader loader){
  if (loader == null) {
    loader=Thread.currentThread().getContextClassLoader();
  }
  if (loader == null) {
    loader=IOUtils.class.getClassLoader();
  }
  try {
    if (!resource.contains(":")) {
      return loader.getResourceAsStream(resource);
    }
    return new URL(resource).openStream();
  }
 catch (  IOException ex) {
    throw new EsHadoopIllegalArgumentException(String.format("Cannot open stream for resource %s",resource),ex);
  }
}
