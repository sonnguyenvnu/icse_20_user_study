/** 
 * Converts input Java ARGB value to native OpenGL format (RGBA on big endian, BGRA on little endian), setting alpha component to opaque (255).
 */
protected static int javaToNativeRGB(int color){
  if (BIG_ENDIAN) {
    return 0xFF | (color << 8);
  }
 else {
    int rb=color & 0x00FF00FF;
    return 0xFF000000 | (rb << 16) | (color & 0x0000FF00) | (rb >> 16);
  }
}
