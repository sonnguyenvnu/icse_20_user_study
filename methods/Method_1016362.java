private final int compares(final byte[] a,final int aoffset,final byte[] b,final int boffset,final int length){
  short i=0;
  byte ac, bc;
  while (i < length) {
    ac=a[aoffset + i];
    bc=b[boffset + i];
    if (ac != bc)     return this.ab[(ac << 7) | bc];
    i++;
  }
  return 0;
}
