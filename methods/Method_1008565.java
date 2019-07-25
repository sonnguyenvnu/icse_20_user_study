public Translog.Operation next() throws IOException {
  if (readOperations < totalOperations) {
    return readOperation();
  }
 else {
    return null;
  }
}
