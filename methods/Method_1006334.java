@Override public void deserialize(DataInput in) throws IOException {
  this.cardinality=0xFFFF & Short.reverseBytes(in.readShort());
  if (this.content.length < this.cardinality) {
    this.content=new short[this.cardinality];
  }
  for (int k=0; k < this.cardinality; ++k) {
    this.content[k]=Short.reverseBytes(in.readShort());
    ;
  }
}
