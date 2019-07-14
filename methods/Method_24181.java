public void loadTexture(){
  boolean needEndDraw=false;
  if (!drawing) {
    beginDraw();
    needEndDraw=true;
  }
  flush();
  if (primaryGraphics) {
    updatePixelSize();
    if (pgl.isFBOBacked()) {
      pgl.syncBackTexture();
    }
 else {
      loadTextureImpl(Texture.POINT,false);
      if (nativePixels == null || nativePixels.length < pixelWidth * pixelHeight) {
        nativePixels=new int[pixelWidth * pixelHeight];
        nativePixelBuffer=PGL.allocateIntBuffer(nativePixels);
      }
      beginPixelsOp(OP_READ);
      try {
        pgl.readPixelsImpl(0,0,pixelWidth,pixelHeight,PGL.RGBA,PGL.UNSIGNED_BYTE,nativePixelBuffer);
      }
 catch (      IndexOutOfBoundsException e) {
      }
      endPixelsOp();
      if (texture != null) {
        texture.setNative(nativePixelBuffer,0,0,pixelWidth,pixelHeight);
      }
    }
  }
 else   if (offscreenMultisample) {
    FrameBuffer ofb=offscreenFramebuffer;
    FrameBuffer mfb=multisampleFramebuffer;
    if (ofb != null && mfb != null) {
      mfb.copyColor(ofb);
    }
  }
  if (needEndDraw) {
    endDraw();
  }
}
