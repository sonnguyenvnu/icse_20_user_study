/** 
 * Copy texture to pixels. Involves video memory to main memory transfer (slow).
 */
public void get(int[] pixels){
  if (pixels == null) {
    throw new RuntimeException("Trying to copy texture to null pixels array");
  }
  if (pixels.length != width * height) {
    throw new RuntimeException("Trying to copy texture to pixels array of " + "wrong size");
  }
  if (tempFbo == null) {
    tempFbo=new FrameBuffer(pg,glWidth,glHeight);
  }
  tempFbo.setColorBuffer(this);
  pg.pushFramebuffer();
  pg.setFramebuffer(tempFbo);
  tempFbo.readPixels();
  pg.popFramebuffer();
  tempFbo.getPixels(pixels);
  convertToARGB(pixels);
  if (invertedX)   flipArrayOnX(pixels,1);
  if (invertedY)   flipArrayOnY(pixels,1);
}
