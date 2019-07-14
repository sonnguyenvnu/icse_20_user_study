public static byte[] long2ByteArray(long l){
  byte[] array=new byte[8];
  int i;
  int shift;
  for (i=0, shift=56; i < 8; i++, shift-=8) {
    array[i]=(byte)(0xFF & (l >> shift));
  }
  return array;
}
