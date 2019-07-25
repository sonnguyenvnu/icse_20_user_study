@Override public int first(){
  assertNonEmpty(numberOfRuns() == 0);
  return toIntUnsigned(getValue(0));
}
