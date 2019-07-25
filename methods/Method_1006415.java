@Override public int rank(short lowbits){
  int x=toIntUnsigned(lowbits);
  int leftover=(x + 1) & 63;
  int answer=0;
  if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
    long[] b=this.bitmap.array();
    for (int k=0; k < (x + 1) / 64; ++k) {
      answer+=Long.bitCount(b[k]);
    }
    if (leftover != 0) {
      answer+=Long.bitCount(b[(x + 1) / 64] << (64 - leftover));
    }
  }
 else {
    for (int k=0; k < (x + 1) / 64; ++k) {
      answer+=Long.bitCount(bitmap.get(k));
    }
    if (leftover != 0) {
      answer+=Long.bitCount(bitmap.get((x + 1) / 64) << (64 - leftover));
    }
  }
  return answer;
}
