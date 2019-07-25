public static InputStream open(String resource,Configuration conf){
  ClassLoader loader=conf.getClassLoader();
  if (loader == null) {
    loader=Thread.currentThread().getContextClassLoader();
  }
  if (loader == null) {
    loader=HadoopIOUtils.class.getClassLoader();
  }
  boolean trace=log.isTraceEnabled();
  try {
    if (!resource.contains(":")) {
      InputStream result=loader.getResourceAsStream(resource);
      if (result != null) {
        if (trace) {
          log.trace(String.format("Loaded resource %s from classpath",resource));
        }
        return result;
      }
      URI[] uris=DistributedCache.getCacheFiles(conf);
      if (uris != null) {
        for (        URI uri : uris) {
          if (uri.toString().contains(resource)) {
            if (trace) {
              log.trace(String.format("Loaded resource %s from distributed cache",resource));
            }
            return uri.toURL().openStream();
          }
        }
      }
    }
    Path p=new Path(resource);
    FileSystem fs=p.getFileSystem(conf);
    return fs.open(p);
  }
 catch (  IOException ex) {
    throw new EsHadoopIllegalArgumentException(String.format("Cannot open stream for resource %s",resource));
  }
}
