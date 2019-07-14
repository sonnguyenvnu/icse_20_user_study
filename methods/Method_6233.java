public static boolean isInternalUri(Uri uri,boolean[] forceBrowser){
  String host=uri.getHost();
  host=host != null ? host.toLowerCase() : "";
  if ("tg".equals(uri.getScheme())) {
    return true;
  }
 else   if ("telegram.dog".equals(host)) {
    String path=uri.getPath();
    if (path != null && path.length() > 1) {
      path=path.substring(1).toLowerCase();
      if (path.startsWith("blog") || path.equals("iv") || path.startsWith("faq") || path.equals("apps") || path.startsWith("s/")) {
        if (forceBrowser != null) {
          forceBrowser[0]=true;
        }
        return false;
      }
      return true;
    }
  }
 else   if ("telegram.me".equals(host) || "t.me".equals(host)) {
    String path=uri.getPath();
    if (path != null && path.length() > 1) {
      path=path.substring(1).toLowerCase();
      if (path.equals("iv") || path.startsWith("s/")) {
        if (forceBrowser != null) {
          forceBrowser[0]=true;
        }
        return false;
      }
      return true;
    }
  }
  return false;
}
