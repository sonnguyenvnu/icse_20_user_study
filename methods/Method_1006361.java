@Override public int rank(short lowbits){
  int x=Util.toIntUnsigned(lowbits);
  int leftover=(x + 1) & 63;
  int answer=0;
  for (int k=0; k < (x + 1) / 64; ++k) {
    answer+=Long.bitCount(bitmap[k]);
  }
  if (leftover != 0) {
    answer+=Long.bitCount(bitmap[(x + 1) / 64] << (64 - leftover));
  }
  return answer;
}
