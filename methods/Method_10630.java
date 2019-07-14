static String checkSuffix(String path){
  if (TextUtils.isEmpty(path)) {
    return ".jpg";
  }
  return path.substring(path.lastIndexOf("."),path.length());
}
