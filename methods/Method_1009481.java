public void process(InputStream in,ZipEntry zipEntry) throws IOException {
  if (entryCallback != null) {
    entryCallback.process(in,zipEntry);
  }
 else {
    process(zipEntry);
  }
}
