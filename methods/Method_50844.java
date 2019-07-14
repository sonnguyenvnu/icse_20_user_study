private long computeClassPathHash(final URL... classpathEntry){
  final Adler32 adler32=new Adler32();
  for (  final URL url : classpathEntry) {
    try (CheckedInputStream inputStream=new CheckedInputStream(url.openStream(),adler32)){
      while (IOUtils.skip(inputStream,Long.MAX_VALUE) == Long.MAX_VALUE) {
      }
    }
 catch (    final FileNotFoundException ignored) {
      LOG.warning("Auxclasspath entry " + url.toString() + " doesn't exist, ignoring it");
    }
catch (    final IOException e) {
      LOG.log(Level.SEVERE,"Incremental analysis can't check auxclasspath contents",e);
      throw new RuntimeException(e);
    }
  }
  return adler32.getValue();
}
