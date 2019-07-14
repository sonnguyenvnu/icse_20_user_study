public static String getHttpUrlExtension(String url,String defaultExt){
  String ext=null;
  String last=Uri.parse(url).getLastPathSegment();
  if (!TextUtils.isEmpty(last) && last.length() > 1) {
    url=last;
  }
  int idx=url.lastIndexOf('.');
  if (idx != -1) {
    ext=url.substring(idx + 1);
  }
  if (ext == null || ext.length() == 0 || ext.length() > 4) {
    ext=defaultExt;
  }
  return ext;
}
