public static String normalizeMediaPath(String path){
  if (TextUtils.isEmpty(EMULATED_STORAGE_SOURCE) || TextUtils.isEmpty(EMULATED_STORAGE_TARGET) || TextUtils.isEmpty(EXTERNAL_STORAGE)) {
    return path;
  }
  if (path.startsWith(EMULATED_STORAGE_SOURCE)) {
    path=path.replace(EMULATED_STORAGE_SOURCE,EMULATED_STORAGE_TARGET);
  }
  return path;
}
