protected void endPixelsOp(){
  if (pixOpChangedFB) {
    popFramebuffer();
    pixOpChangedFB=false;
  }
  if (readBufferSupported)   pgl.readBuffer(getCurrentFB().getDefaultReadBuffer());
  if (drawBufferSupported)   pgl.drawBuffer(getCurrentFB().getDefaultDrawBuffer());
  pixelsOp=OP_NONE;
}
