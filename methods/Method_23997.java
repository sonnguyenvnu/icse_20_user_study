/** 
 * Converts input Java ARGB value to native OpenGL format (RGBA on big endian, BGRA on little endian).
 */
protected static int javaToNativeARGB(int color){
  if (BIG_ENDIAN) {
    return (color >>> 24) | (color << 8);
  }
 else {
    int rb=color & 0x00FF00FF;
    return (color & 0xFF00FF00) | (rb << 16) | (rb >> 16);
  }
}
