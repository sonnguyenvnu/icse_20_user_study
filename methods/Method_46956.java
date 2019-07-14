public static byte byteOfInt(int value,int which){
  int shift=which * 8;
  return (byte)(value >> shift);
}
