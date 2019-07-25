@Override public int last(){
  assertNonEmpty(cardinality == 0);
  int i=bitmap.length - 1;
  while (i > 0 && bitmap[i] == 0) {
    --i;
  }
  return (i + 1) * 64 - Long.numberOfLeadingZeros(bitmap[i]) - 1;
}
