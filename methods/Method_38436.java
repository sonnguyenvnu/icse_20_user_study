/** 
 * Locates gzipped version of bundle file. If gzip file does not exist, it will be created.
 */
public File lookupGzipBundleFile(final File file) throws IOException {
  String path=file.getPath() + ZipUtil.GZIP_EXT;
  File gzipFile=new File(path);
  if (!gzipFile.exists()) {
    if (log.isDebugEnabled()) {
      log.debug("gzip bundle to " + path);
    }
    ZipUtil.gzip(file);
  }
  return gzipFile;
}
