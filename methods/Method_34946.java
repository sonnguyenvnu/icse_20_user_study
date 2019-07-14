public static void putIntInByteArray(int value,byte[] buf,int offset){
  for (int i=0; i < 4; i++) {
    int valueOffset=(3 - i) * 8;
    buf[offset + i]=(byte)((value >>> valueOffset) & 0xFF);
  }
}
