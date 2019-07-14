protected void loadTextureImpl(int sampling,boolean mipmap){
  updatePixelSize();
  if (pixelWidth == 0 || pixelHeight == 0)   return;
  if (texture == null || texture.contextIsOutdated()) {
    Texture.Parameters params=new Texture.Parameters(ARGB,sampling,mipmap);
    texture=new Texture(this,pixelWidth,pixelHeight,params);
    texture.invertedY(true);
    texture.colorBuffer(true);
    setCache(this,texture);
  }
}
