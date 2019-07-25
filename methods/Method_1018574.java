public static void smix(byte[] B,int Bi,int r,int N,byte[] V,byte[] XY){
  int Xi=0;
  int Yi=128 * r;
  int i;
  arraycopy(B,Bi,XY,Xi,128 * r);
  for (i=0; i < N; i++) {
    arraycopy(XY,Xi,V,i * (128 * r),128 * r);
    blockmix_salsa8(XY,Xi,Yi,r);
  }
  for (i=0; i < N; i++) {
    int j=integerify(XY,Xi,r) & (N - 1);
    blockxor(V,j * (128 * r),XY,Xi,128 * r);
    blockmix_salsa8(XY,Xi,Yi,r);
  }
  arraycopy(XY,Xi,B,Bi,128 * r);
}
