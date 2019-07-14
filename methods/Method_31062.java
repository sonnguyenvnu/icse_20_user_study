public static boolean isWebScheme(Uri uri){
  String scheme=uri.getScheme();
  if (TextUtils.isEmpty(scheme)) {
    return false;
  }
switch (uri.getScheme()) {
case "http":
case "https":
    return true;
default :
  return false;
}
}
