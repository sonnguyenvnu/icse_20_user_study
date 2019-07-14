/** 
 * @param maskArray array of integers used as the alpha channel, needs to bethe same length as the image's pixel array.
 */
public void mask(int maskArray[]){
  loadPixels();
  if (maskArray.length != pixels.length) {
    throw new IllegalArgumentException("mask() can only be used with an image that's the same size.");
  }
  for (int i=0; i < pixels.length; i++) {
    pixels[i]=((maskArray[i] & 0xff) << 24) | (pixels[i] & 0xffffff);
  }
  format=ARGB;
  updatePixels();
}
