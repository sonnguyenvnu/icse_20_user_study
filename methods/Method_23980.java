protected void restoreFirstFrame(){
  if (firstFrame == null)   return;
  IntBuffer tex=allocateIntBuffer(1);
  genTextures(1,tex);
  int w, h;
  float scale=getPixelScale();
  if (hasNpotTexSupport()) {
    w=(int)(scale * graphics.width);
    h=(int)(scale * graphics.height);
  }
 else {
    w=nextPowerOfTwo((int)(scale * graphics.width));
    h=nextPowerOfTwo((int)(scale * graphics.height));
  }
  bindTexture(TEXTURE_2D,tex.get(0));
  texParameteri(TEXTURE_2D,TEXTURE_MIN_FILTER,NEAREST);
  texParameteri(TEXTURE_2D,TEXTURE_MAG_FILTER,NEAREST);
  texParameteri(TEXTURE_2D,TEXTURE_WRAP_S,CLAMP_TO_EDGE);
  texParameteri(TEXTURE_2D,TEXTURE_WRAP_T,CLAMP_TO_EDGE);
  texImage2D(TEXTURE_2D,0,RGBA,w,h,0,RGBA,UNSIGNED_BYTE,null);
  texSubImage2D(TEXTURE_2D,0,0,0,graphics.width,graphics.height,RGBA,UNSIGNED_BYTE,firstFrame);
  drawTexture(TEXTURE_2D,tex.get(0),w,h,0,0,graphics.width,graphics.height,0,0,(int)(scale * graphics.width),(int)(scale * graphics.height),0,0,graphics.width,graphics.height);
  deleteTextures(1,tex);
  firstFrame.clear();
  firstFrame=null;
}
