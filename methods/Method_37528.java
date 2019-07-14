/** 
 * Scans URLs. If (#ignoreExceptions} is set, exceptions per one URL will be ignored and loops continues.
 */
public ClassScanner scan(final URL... urls){
  for (  final URL url : urls) {
    final File file=FileUtil.toContainerFile(url);
    if (file == null) {
      if (!ignoreException) {
        throw new FindFileException("URL is not a valid file: " + url);
      }
    }
 else {
      filesToScan.add(file);
    }
  }
  return this;
}
