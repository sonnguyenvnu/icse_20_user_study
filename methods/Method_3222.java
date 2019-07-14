@Override public int readWordIndex() throws IOException {
  int id=nextId();
  while (id == -4) {
    id=nextId();
  }
  return id;
}
