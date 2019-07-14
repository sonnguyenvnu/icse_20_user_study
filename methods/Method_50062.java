/** 
 * Adds next GIF frame to the specified position. The frame is not written immediately, but is actually deferred until the next frame is received so that timing data can be inserted. Invoking <code>finish()</code> flushes all frames. If <code>setSize</code> was invoked, the size is used for all subsequent frames. Otherwise, the actual size of the image is used for each frame. <p> See page 11 of http://giflib.sourceforge.net/gif89.txt for the position of the frame
 * @param im BufferedImage containing frame to write.
 * @param x  Column number, in pixels, of the left edge of the image, with respect to the leftedge of the Logical Screen.
 * @param y  Row number, in pixels, of the top edge of the image with respect to the top edge ofthe Logical Screen.
 * @return true if successful.
 */
public boolean addFrame(@Nullable Bitmap im,int x,int y){
  if ((im == null) || !started) {
    return false;
  }
  boolean ok=true;
  try {
    if (sizeSet) {
      setFrameSize(fixedWidth,fixedHeight);
    }
 else {
      setFrameSize(im.getWidth(),im.getHeight());
    }
    image=im;
    getImagePixels();
    analyzePixels();
    if (firstFrame) {
      writeLSD();
      writePalette();
      if (repeat >= 0) {
        writeNetscapeExt();
      }
    }
    writeGraphicCtrlExt();
    writeImageDesc(x,y);
    if (!firstFrame) {
      writePalette();
    }
    writePixels();
    firstFrame=false;
  }
 catch (  IOException e) {
    ok=false;
  }
  return ok;
}
