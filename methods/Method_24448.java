public void setNative(IntBuffer pixBuf,int x,int y,int w,int h){
  if (pixBuf == null) {
    pixBuf=null;
    PGraphics.showWarning("The pixel buffer is null.");
    return;
  }
  if (pixBuf.capacity() < w * h) {
    PGraphics.showWarning("The pixel bufer has a length of " + pixBuf.capacity() + ", but it should be at least " + w * h);
    return;
  }
  if (pixBuf.capacity() == 0) {
    return;
  }
  boolean enabledTex=false;
  if (!pgl.texturingIsEnabled(glTarget)) {
    pgl.enableTexturing(glTarget);
    enabledTex=true;
  }
  pgl.bindTexture(glTarget,glName);
  pgl.texSubImage2D(glTarget,0,x,y,w,h,PGL.RGBA,PGL.UNSIGNED_BYTE,pixBuf);
  fillEdges(x,y,w,h);
  if (usingMipmaps) {
    if (PGraphicsOpenGL.autoMipmapGenSupported) {
      pgl.generateMipmap(glTarget);
    }
 else {
      manualMipmap();
    }
  }
  pgl.bindTexture(glTarget,0);
  if (enabledTex) {
    pgl.disableTexturing(glTarget);
  }
  updateTexels(x,y,w,h);
}
