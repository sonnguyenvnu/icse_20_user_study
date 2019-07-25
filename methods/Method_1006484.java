@Override protected boolean contains(ArrayContainer arrayContainer){
  final int cardinality=getCardinality();
  final int runCount=numberOfRuns();
  if (arrayContainer.getCardinality() > cardinality) {
    return false;
  }
  int ia=0, ir=0;
  while (ia < arrayContainer.getCardinality() && ir < runCount) {
    int start=toIntUnsigned(this.getValue(ir));
    int stop=start + toIntUnsigned(getLength(ir));
    int ac=toIntUnsigned(arrayContainer.content[ia]);
    if (ac < start) {
      return false;
    }
 else     if (ac > stop) {
      ++ir;
    }
 else {
      ++ia;
    }
  }
  return ia == arrayContainer.getCardinality();
}
