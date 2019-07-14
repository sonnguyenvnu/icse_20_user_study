public static float getHeight(long measureOutput){
  return Float.intBitsToFloat((int)(0xFFFFFFFF & measureOutput));
}
