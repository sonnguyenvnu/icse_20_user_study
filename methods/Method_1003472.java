public static String[] list(String path,AssetManager assetManager) throws IOException {
  String[] files=assetManager.list(path);
  Arrays.sort(files);
  return files;
}
