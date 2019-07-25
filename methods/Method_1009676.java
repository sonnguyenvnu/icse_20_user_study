@Override public void destroy(File path,Options options) throws IOException {
  FileUtils.deleteRecursively(path);
}
