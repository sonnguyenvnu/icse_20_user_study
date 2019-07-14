/** 
 * Converts input array of Java ARGB values representing an image of width x height resolution to native OpenGL format (RGBA on big endian, BGRA on little endian), while setting alpha component of all pixels to opaque (255). It also rearranges the elements in the array so that the image is flipped vertically.
 */
protected static void javaToNativeRGB(int[] pixels,int width,int height){
  int index=0;
  int yindex=(height - 1) * width;
  for (int y=0; y < height / 2; y++) {
    for (int x=0; x < width; x++) {
      int pixy=pixels[yindex];
      int pixi=pixels[index];
      if (BIG_ENDIAN) {
        pixels[index]=0xFF | (pixy << 8);
        pixels[yindex]=0xFF | (pixi << 8);
      }
 else {
        int rbi=pixi & 0x00FF00FF;
        int rby=pixy & 0x00FF00FF;
        pixels[index]=0xFF000000 | (rby << 16) | (pixy & 0x0000FF00) | (rby >> 16);
        pixels[yindex]=0xFF000000 | (rbi << 16) | (pixi & 0x0000FF00) | (rbi >> 16);
      }
      index++;
      yindex++;
    }
    yindex-=width * 2;
  }
  if ((height % 2) == 1) {
    index=(height / 2) * width;
    for (int x=0; x < width; x++) {
      int pixi=pixels[index];
      if (BIG_ENDIAN) {
        pixels[index]=0xFF | (pixi << 8);
      }
 else {
        int rbi=pixi & 0x00FF00FF;
        pixels[index]=0xFF000000 | (rbi << 16) | (pixi & 0x0000FF00) | (rbi >> 16);
      }
      index++;
    }
  }
}
