static boolean isImage(String path){
  if (TextUtils.isEmpty(path)) {
    return false;
  }
  String suffix=path.substring(path.lastIndexOf(".") + 1,path.length());
  return format.contains(suffix.toLowerCase());
}
