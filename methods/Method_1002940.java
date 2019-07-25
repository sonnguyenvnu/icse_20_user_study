private InputStream open() throws IOException {
  if (file != null) {
    return new FileInputStream(file);
  }
  ClassLoader classLoader=this.classLoader;
  if (classLoader == null) {
    classLoader=Thread.currentThread().getContextClassLoader();
  }
  if (classLoader == null) {
    classLoader=getClass().getClassLoader();
  }
  assert classLoader != null : "classLoader";
  assert resourcePath != null : "resourcePath";
  final InputStream is=classLoader.getResourceAsStream(resourcePath);
  if (is == null) {
    throw new FileNotFoundException("Resource not found: " + resourcePath);
  }
  return is;
}
