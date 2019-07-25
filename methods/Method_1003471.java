public static boolean exists(String fileName,String path,AssetManager assetManager) throws IOException {
  for (  String currentFileName : assetManager.list(path)) {
    if (currentFileName.equals(fileName)) {
      return true;
    }
  }
  return false;
}
