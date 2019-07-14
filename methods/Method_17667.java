public static float getWidth(long measureOutput){
  return Float.intBitsToFloat((int)(0xFFFFFFFF & (measureOutput >> 32)));
}
