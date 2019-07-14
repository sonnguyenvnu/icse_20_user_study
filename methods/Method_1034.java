private void skipExtension() throws IOException {
  int size;
  do {
    size=readBlock();
  }
 while (size > 0);
}
