private int nextId() throws IOException {
  if (raf.length() - raf.getFilePointer() >= 4) {
    int id=raf.readInt();
    return id < 0 ? id : table[id];
  }
  return -2;
}
