public void transform(InputStream in,ZipEntry zipEntry,ZipOutputStream out) throws IOException {
  addEntry(source,out);
}
