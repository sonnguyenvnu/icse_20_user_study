public final byte[] uncardinal(long c){
  c=c >> 3;
  final byte[] b=new byte[12];
  for (int p=9; p >= 0; p--) {
    b[p]=this.alpha[(int)(c & 0x3fL)];
    c=c >> 6;
  }
  b[10]=this.alpha[0x3f];
  b[11]=this.alpha[0x3f];
  return b;
}
