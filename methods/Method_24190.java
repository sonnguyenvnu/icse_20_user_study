protected Object initCache(PImage img){
  if (!checkGLThread()) {
    return null;
  }
  Texture tex=(Texture)getCache(img);
  if (tex == null || tex.contextIsOutdated()) {
    tex=addTexture(img);
    if (tex != null) {
      boolean dispose=img.pixels == null;
      img.loadPixels();
      tex.set(img.pixels,img.format);
      img.setModified();
      if (dispose) {
        img.pixels=null;
        img.loaded=false;
      }
    }
  }
  return tex;
}
