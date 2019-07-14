public static byte[] int2ByteArray(int value){
  byte[] b=new byte[4];
  for (int i=0; i < 4; i++) {
    int offset=(3 - i) * 8;
    b[i]=(byte)((value >>> offset) & 0xFF);
  }
  return b;
}
