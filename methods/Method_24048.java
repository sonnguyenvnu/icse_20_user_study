public void readPixels(int x,int y,int width,int height,int format,int type,long offset){
  boolean multisampled=isMultisampled() || graphics.offscreenMultisample;
  boolean depthReadingEnabled=graphics.getHint(PConstants.ENABLE_BUFFER_READING);
  boolean depthRequested=format == STENCIL_INDEX || format == DEPTH_COMPONENT || format == DEPTH_STENCIL;
  if (multisampled && depthRequested && !depthReadingEnabled) {
    PGraphics.showWarning(DEPTH_READING_NOT_ENABLED_ERROR);
    return;
  }
  graphics.beginReadPixels();
  readPixelsImpl(x,y,width,height,format,type,offset);
  graphics.endReadPixels();
}
