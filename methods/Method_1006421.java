@Override public int last(){
  assertNonEmpty(cardinality == 0);
  long lastNonZeroWord;
  int i=bitmap.limit() - 1;
  if (BufferUtil.isBackedBySimpleArray(bitmap)) {
    long[] array=this.bitmap.array();
    while (i > 0 && array[i] == 0) {
      --i;
    }
    lastNonZeroWord=array[i];
  }
 else {
    while (i > 0 && bitmap.get(i) == 0) {
      --i;
    }
    lastNonZeroWord=bitmap.get(i);
  }
  return (i + 1) * 64 - Long.numberOfLeadingZeros(lastNonZeroWord) - 1;
}
