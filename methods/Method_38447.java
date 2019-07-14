/** 
 * Returns the content with all relative URLs fixed.
 */
protected String fixCssRelativeUrls(final String content,final String src){
  final String path=FileNameUtil.getPath(src);
  final Matcher matcher=CSS_URL_PATTERN.matcher(content);
  final StringBuilder sb=new StringBuilder(content.length());
  int start=0;
  while (matcher.find()) {
    sb.append(content,start,matcher.start());
    final String matchedUrl=StringUtil.removeChars(matcher.group(1),"'\"");
    final String url;
    if (matchedUrl.startsWith("https://") || matchedUrl.startsWith("http://") || matchedUrl.startsWith("data:")) {
      url="url('" + matchedUrl + "')";
    }
 else {
      url=fixRelativeUrl(matchedUrl,path);
    }
    sb.append(url);
    start=matcher.end();
  }
  sb.append(content.substring(start));
  return sb.toString();
}
