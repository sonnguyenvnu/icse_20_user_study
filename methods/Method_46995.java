public static boolean isFileExtractable(String path){
  String type=getExtension(path);
  return isZip(type) || isTar(type) || isRar(type) || isGzippedTar(type);
}
