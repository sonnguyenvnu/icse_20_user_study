@Override public void deserialize(DataInput in) throws IOException {
  this.cardinality=0;
  for (int k=0; k < bitmap.length; ++k) {
    long w=Long.reverseBytes(in.readLong());
    bitmap[k]=w;
    this.cardinality+=Long.bitCount(w);
  }
}
