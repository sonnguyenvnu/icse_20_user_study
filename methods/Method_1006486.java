@Override public void deserialize(DataInput in) throws IOException {
  nbrruns=Short.reverseBytes(in.readShort());
  if (valueslength.length < 2 * nbrruns) {
    valueslength=new short[2 * nbrruns];
  }
  for (int k=0; k < 2 * nbrruns; ++k) {
    this.valueslength[k]=Short.reverseBytes(in.readShort());
  }
}
