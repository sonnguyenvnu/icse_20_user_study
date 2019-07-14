/** 
 * For a given URL (optionally quoted), produces CSS URL where relative paths are fixed and prefixed with offsetPath.
 */
protected String fixRelativeUrl(final String url,final String offsetPath){
  final StringBuilder res=new StringBuilder();
  res.append("url('");
  if (!url.startsWith(StringPool.SLASH)) {
    res.append("../").append(offsetPath);
  }
  res.append(url).append("')");
  return res.toString();
}
