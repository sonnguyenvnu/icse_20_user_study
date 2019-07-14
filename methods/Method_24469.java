/** 
 * Reorders a pixel array in the given format into the order required by OpenGL (RGBA) and stores it into rgbaPixels. The width and height parameters are used in the YUV420 to RBGBA conversion.
 * @param pixels int[]
 * @param format int
 * @param w int
 * @param h int
 */
protected void convertToRGBA(int[] pixels,int format,int w,int h){
  if (PGL.BIG_ENDIAN) {
switch (format) {
case ALPHA:
      for (int i=0; i < pixels.length; i++) {
        rgbaPixels[i]=0xFFFFFF00 | pixels[i];
      }
    break;
case RGB:
  for (int i=0; i < pixels.length; i++) {
    int pixel=pixels[i];
    rgbaPixels[i]=(pixel << 8) | 0xFF;
  }
break;
case ARGB:
for (int i=0; i < pixels.length; i++) {
int pixel=pixels[i];
rgbaPixels[i]=(pixel << 8) | ((pixel >> 24) & 0xFF);
}
break;
}
}
 else {
switch (format) {
case ALPHA:
for (int i=0; i < pixels.length; i++) {
rgbaPixels[i]=(pixels[i] << 24) | 0x00FFFFFF;
}
break;
case RGB:
for (int i=0; i < pixels.length; i++) {
int pixel=pixels[i];
rgbaPixels[i]=0xFF000000 | ((pixel & 0xFF) << 16) | ((pixel & 0xFF0000) >> 16) | (pixel & 0x0000FF00);
}
break;
case ARGB:
for (int i=0; i < pixels.length; i++) {
int pixel=pixels[i];
rgbaPixels[i]=((pixel & 0xFF) << 16) | ((pixel & 0xFF0000) >> 16) | (pixel & 0xFF00FF00);
}
break;
}
}
rgbaPixUpdateCount++;
}
