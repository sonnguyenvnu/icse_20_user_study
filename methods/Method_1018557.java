private static void randombytes(byte[] b,int len){
  byte[] r=new byte[len];
  prng.nextBytes(r);
  System.arraycopy(r,0,b,0,len);
}
