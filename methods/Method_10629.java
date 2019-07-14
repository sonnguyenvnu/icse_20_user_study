static boolean isJPG(String path){
  if (TextUtils.isEmpty(path)) {
    return false;
  }
  String suffix=path.substring(path.lastIndexOf("."),path.length()).toLowerCase();
  return suffix.contains(JPG) || suffix.contains(JPEG);
}
