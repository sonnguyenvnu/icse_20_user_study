/** 
 * Update the pixels[] buffer to the PGraphics image. <P> Unlike in PImage, where updatePixels() only requests that the update happens, in PGraphicsJava2D, this will happen immediately.
 */
@Override public void updatePixels(int x,int y,int c,int d){
  if ((x != 0) || (y != 0) || (c != pixelWidth) || (d != pixelHeight)) {
    showVariationWarning("updatePixels(x, y, w, h)");
  }
  if (pixels != null) {
    getRaster().setDataElements(0,0,pixelWidth,pixelHeight,pixels);
  }
  modified=true;
}
