@Override public DavResourceLocator _XXXXX_(String prefix,String href){
  StringBuilder b=new StringBuilder();
  if (prefix != null && prefix.length() > 0) {
    b.append(prefix);
    if (!prefix.endsWith("/")) {
      b.append('/');
    }
    if (href.startsWith(prefix)) {
      href=href.substring(prefix.length());
    }
  }
  if (href == null || "".equals(href)) {
    href="/";
  }
  final String repository=RepositoryPathUtil.getRepositoryName(href);
  return new ArchivaDavResourceLocator(b.toString(),Text.unescape(href),repository,this);
}