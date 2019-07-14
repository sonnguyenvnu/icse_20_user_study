/** 
 * Converts input native OpenGL value (RGBA on big endian, ABGR on little endian) to Java ARGB.
 */
protected static int nativeToJavaARGB(int color){
  if (BIG_ENDIAN) {
    return (color >>> 8) | (color << 24);
  }
 else {
    int rb=color & 0x00FF00FF;
    return (color & 0xFF00FF00) | (rb << 16) | (rb >> 16);
  }
}
