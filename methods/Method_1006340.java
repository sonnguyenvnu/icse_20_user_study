@Override public void serialize(DataOutput out) throws IOException {
  out.writeShort(Short.reverseBytes((short)this.cardinality));
  for (int k=0; k < this.cardinality; ++k) {
    out.writeShort(Short.reverseBytes(this.content[k]));
  }
}
