/** 
 * Converts input native OpenGL value (RGBA on big endian, ABGR on little endian) to Java RGB, so that the alpha component of the result is set to opaque (255).
 */
protected static int nativeToJavaRGB(int color){
  if (BIG_ENDIAN) {
    return (color >>> 8) | 0xFF000000;
  }
 else {
    int rb=color & 0x00FF00FF;
    return 0xFF000000 | (rb << 16) | (color & 0x0000FF00) | (rb >> 16);
  }
}
