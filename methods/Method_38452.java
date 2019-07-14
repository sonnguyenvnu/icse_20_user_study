@Override protected boolean processActionPath(final HttpServletRequest servletRequest,final HttpServletResponse servletResponse,final String actionPath) throws IOException {
  String bundlePath='/' + bundlesManager.getStaplerPath() + '/';
  if (!actionPath.startsWith(bundlePath)) {
    return false;
  }
  String bundleId=actionPath.substring(bundlePath.length());
  File file=bundlesManager.lookupBundleFile(bundleId);
  if (log.isDebugEnabled()) {
    log.debug("bundle: " + bundleId);
  }
  int ndx=bundleId.lastIndexOf('.');
  String extension=bundleId.substring(ndx + 1);
  String contentType=MimeTypes.getMimeType(extension);
  servletResponse.setContentType(contentType);
  if (useGzip && ServletUtil.isGzipSupported(servletRequest)) {
    file=bundlesManager.lookupGzipBundleFile(file);
    servletResponse.setHeader("Content-Encoding","gzip");
  }
  if (!file.exists()) {
    throw new IOException("bundle not found: " + bundleId);
  }
  servletResponse.setHeader("Content-Length",String.valueOf(file.length()));
  servletResponse.setHeader("Last-Modified",TimeUtil.formatHttpDate(file.lastModified()));
  if (cacheMaxAge > 0) {
    servletResponse.setHeader("Cache-Control","max-age=" + cacheMaxAge);
  }
  sendBundleFile(servletResponse,file);
  return true;
}
