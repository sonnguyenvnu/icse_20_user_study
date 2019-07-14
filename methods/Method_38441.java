private String downloadString(final String localUrl) throws IOException {
  String content;
  try {
    content=NetUtil.downloadString(localUrl,localFilesEncoding);
  }
 catch (  IOException ioex) {
    if (notFoundExceptionEnabled) {
      throw ioex;
    }
    if (log.isWarnEnabled()) {
      log.warn("Download failed: " + localUrl + "; " + ioex.getMessage());
    }
    content=null;
  }
  return content;
}
