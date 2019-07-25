public void reset() throws IOException {
  for (int i=0; i < checker.length; i++) {
    checker[i].getDiskGraph().reset();
  }
}
