/** 
 * Check the alpha on an image, using a really primitive loop.
 */
protected void checkAlpha(){
  if (pixels == null)   return;
  for (int i=0; i < pixels.length; i++) {
    if ((pixels[i] & 0xff000000) != 0xff000000) {
      format=ARGB;
      break;
    }
  }
}
