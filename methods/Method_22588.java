public InputStream getContentStream(String path) throws FileNotFoundException {
  return new FileInputStream(getContentFile(path));
}
