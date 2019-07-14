private void ignoreColorTable(int numColors) throws IOException {
  for (int i=0, N=3 * numColors; i < N; i++) {
    readNextByte();
  }
}
