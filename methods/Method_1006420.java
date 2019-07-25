@Override public int first(){
  assertNonEmpty(cardinality == 0);
  long firstNonZeroWord;
  int i=0;
  if (BufferUtil.isBackedBySimpleArray(bitmap)) {
    long[] array=bitmap.array();
    while (array[i] == 0) {
      ++i;
    }
    firstNonZeroWord=array[i];
  }
 else {
    i=bitmap.position();
    while (bitmap.get(i) == 0) {
      ++i;
    }
    firstNonZeroWord=bitmap.get(i);
  }
  return i * 64 + numberOfTrailingZeros(firstNonZeroWord);
}
