public static int integerify(byte[] B,int Bi,int r){
  int n;
  Bi+=(2 * r - 1) * 64;
  n=(B[Bi + 0] & 0xff) << 0;
  n|=(B[Bi + 1] & 0xff) << 8;
  n|=(B[Bi + 2] & 0xff) << 16;
  n|=(B[Bi + 3] & 0xff) << 24;
  return n;
}
