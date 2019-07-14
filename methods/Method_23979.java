protected void saveFirstFrame(){
  firstFrame=allocateDirectIntBuffer(graphics.width * graphics.height);
  if (hasReadBuffer())   readBuffer(BACK);
  readPixelsImpl(0,0,graphics.width,graphics.height,RGBA,UNSIGNED_BYTE,firstFrame);
}
