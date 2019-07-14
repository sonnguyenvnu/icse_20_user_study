protected void drawPixels(int x,int y,int w,int h){
  int len=w * h;
  if (nativePixels == null || nativePixels.length < len) {
    nativePixels=new int[len];
    nativePixelBuffer=PGL.allocateIntBuffer(nativePixels);
  }
  try {
    if (0 < x || 0 < y || w < pixelWidth || h < pixelHeight) {
      int offset0=y * pixelWidth + x;
      int offset1=0;
      for (int yc=y; yc < y + h; yc++) {
        System.arraycopy(pixels,offset0,nativePixels,offset1,w);
        offset0+=pixelWidth;
        offset1+=w;
      }
    }
 else {
      PApplet.arrayCopy(pixels,0,nativePixels,0,len);
    }
    PGL.javaToNativeARGB(nativePixels,w,h);
  }
 catch (  ArrayIndexOutOfBoundsException e) {
  }
  PGL.putIntArray(nativePixelBuffer,nativePixels);
  if (primaryGraphics && !pgl.isFBOBacked()) {
    loadTextureImpl(POINT,false);
  }
  boolean needToDrawTex=primaryGraphics && (!pgl.isFBOBacked() || (pgl.isFBOBacked() && pgl.isMultisampled())) || offscreenMultisample;
  if (texture == null)   return;
  if (needToDrawTex) {
    int tw=PApplet.min(texture.glWidth - x,w);
    int th=PApplet.min(texture.glHeight - y,h);
    pgl.copyToTexture(texture.glTarget,texture.glFormat,texture.glName,x,y,tw,th,nativePixelBuffer);
    beginPixelsOp(OP_WRITE);
    drawTexture(x,y,w,h);
    endPixelsOp();
  }
 else {
    pgl.copyToTexture(texture.glTarget,texture.glFormat,texture.glName,x,pixelHeight - (y + h),w,h,nativePixelBuffer);
  }
}
