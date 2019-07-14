/** 
 * Reorders an OpenGL pixel array (RGBA) into ARGB. The array must be of size width * height.
 * @param pixels int[]
 */
protected void convertToARGB(int[] pixels){
  int t=0;
  int p=0;
  if (PGL.BIG_ENDIAN) {
    for (int y=0; y < height; y++) {
      for (int x=0; x < width; x++) {
        int pixel=pixels[p++];
        pixels[t++]=(pixel >>> 8) | ((pixel << 24) & 0xFF000000);
      }
    }
  }
 else {
    for (int y=0; y < height; y++) {
      for (int x=0; x < width; x++) {
        int pixel=pixels[p++];
        pixels[t++]=((pixel & 0xFF) << 16) | ((pixel & 0xFF0000) >> 16) | (pixel & 0xFF00FF00);
      }
    }
  }
}
