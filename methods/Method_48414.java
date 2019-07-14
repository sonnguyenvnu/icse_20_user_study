public static void putInt(byte[] array,int offset,final int value){
  array[offset++]=(byte)((value >> 24) & 0xFF);
  array[offset++]=(byte)((value >> 16) & 0xFF);
  array[offset++]=(byte)((value >> 8) & 0xFF);
  array[offset]=(byte)(value & 0xFF);
}
