public static int getInt(byte[] array,int offset){
  return (array[offset++] & 0xFF) << 24 | (array[offset++] & 0xFF) << 16 | (array[offset++] & 0xFF) << 8 | array[offset] & 0xFF;
}
