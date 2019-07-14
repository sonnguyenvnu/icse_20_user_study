private static void putKeys(int resId,String... mimeTypes){
  for (  String type : mimeTypes) {
    put(type,resId);
  }
}
