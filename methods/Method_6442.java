public static File getDirectory(int type){
  File dir=mediaDirs.get(type);
  if (dir == null && type != FileLoader.MEDIA_DIR_CACHE) {
    dir=mediaDirs.get(FileLoader.MEDIA_DIR_CACHE);
  }
  try {
    if (!dir.isDirectory()) {
      dir.mkdirs();
    }
  }
 catch (  Exception e) {
  }
  return dir;
}
