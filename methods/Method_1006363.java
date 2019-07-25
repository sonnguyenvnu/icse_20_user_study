@Override public void serialize(DataOutput out) throws IOException {
  for (  long w : bitmap) {
    out.writeLong(Long.reverseBytes(w));
  }
}
