@Override public int first(){
  assertNonEmpty(cardinality == 0);
  int i=0;
  while (i < bitmap.length - 1 && bitmap[i] == 0) {
    ++i;
  }
  return i * 64 + numberOfTrailingZeros(bitmap[i]);
}
