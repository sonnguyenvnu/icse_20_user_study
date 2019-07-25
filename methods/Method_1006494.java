@Override public short select(int j){
  int offset=0;
  for (int k=0; k < this.nbrruns; ++k) {
    int nextOffset=offset + toIntUnsigned(getLength(k)) + 1;
    if (nextOffset > j) {
      return (short)(getValue(k) + (j - offset));
    }
    offset=nextOffset;
  }
  throw new IllegalArgumentException("Cannot select " + j + " since cardinality is " + getCardinality());
}
