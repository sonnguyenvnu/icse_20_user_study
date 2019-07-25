@Override public int last(){
  assertNonEmpty(numberOfRuns() == 0);
  int index=numberOfRuns() - 1;
  int start=toIntUnsigned(getValue(index));
  int length=toIntUnsigned(getLength(index));
  return start + length;
}
