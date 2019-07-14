/** 
 * Creates bundle file by loading resource files content. If bundle file already exist it will not be recreated!
 */
protected void createBundle(final String contextPath,final String actionPath,final String bundleId,final List<String> sources) throws IOException {
  final File bundleFile=createBundleFile(bundleId);
  if (bundleFile.exists()) {
    return;
  }
  StringBand sb=new StringBand(sources.size() * 2);
  for (  String src : sources) {
    if (sb.length() != 0) {
      sb.append(StringPool.NEWLINE);
    }
    String content;
    if (isExternalResource(src)) {
      content=downloadString(src);
    }
 else {
      if (!downloadLocal) {
        String localFile=webRoot;
        if (src.startsWith(contextPath + '/')) {
          src=src.substring(contextPath.length());
        }
        if (src.startsWith(StringPool.SLASH)) {
          localFile+=src;
        }
 else {
          localFile+='/' + FileNameUtil.getPathNoEndSeparator(actionPath) + '/' + src;
        }
        int qmndx=localFile.indexOf('?');
        if (qmndx != -1) {
          localFile=localFile.substring(0,qmndx);
        }
        try {
          content=FileUtil.readString(localFile);
        }
 catch (        IOException ioex) {
          if (notFoundExceptionEnabled) {
            throw ioex;
          }
          if (log.isWarnEnabled()) {
            log.warn(ioex.getMessage());
          }
          content=null;
        }
      }
 else {
        String localUrl=localAddressAndPort;
        if (src.startsWith(StringPool.SLASH)) {
          localUrl+=contextPath + src;
        }
 else {
          localUrl+=contextPath + FileNameUtil.getPath(actionPath) + '/' + src;
        }
        content=downloadString(localUrl);
      }
      if (content != null) {
        if (isCssResource(src)) {
          content=fixCssRelativeUrls(content,src);
        }
      }
    }
    if (content != null) {
      content=onResourceContent(content);
      sb.append(content);
    }
  }
  FileUtil.writeString(bundleFile,sb.toString());
  if (log.isInfoEnabled()) {
    log.info("Bundle created: " + bundleId);
  }
}
