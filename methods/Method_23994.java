/** 
 * Converts input array of native OpenGL values (RGBA on big endian, ABGR on little endian) representing an image of width x height resolution to Java ARGB. It also rearranges the elements in the array so that the image is flipped vertically.
 */
protected static void nativeToJavaARGB(int[] pixels,int width,int height){
  int index=0;
  int yindex=(height - 1) * width;
  for (int y=0; y < height / 2; y++) {
    for (int x=0; x < width; x++) {
      int pixy=pixels[yindex];
      int pixi=pixels[index];
      if (BIG_ENDIAN) {
        pixels[index]=(pixy >>> 8) | (pixy << 24);
        pixels[yindex]=(pixi >>> 8) | (pixi << 24);
      }
 else {
        int rbi=pixi & 0x00FF00FF;
        int rby=pixy & 0x00FF00FF;
        pixels[index]=(pixy & 0xFF00FF00) | (rby << 16) | (rby >> 16);
        pixels[yindex]=(pixi & 0xFF00FF00) | (rbi << 16) | (rbi >> 16);
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
        pixels[index]=(pixi >>> 8) | (pixi << 24);
      }
 else {
        int rbi=pixi & 0x00FF00FF;
        pixels[index]=(pixi & 0xFF00FF00) | (rbi << 16) | (rbi >> 16);
      }
      index++;
    }
  }
}
