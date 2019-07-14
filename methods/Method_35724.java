private InputStream createInputStream() throws IOException {
  if (new File(path).isFile()) {
    return new FileInputStream(path);
  }
 else {
    return Resources.getResource(path).openStream();
  }
}
