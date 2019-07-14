protected Texture addTexture(PImage img,Texture.Parameters params){
  if (img.width == 0 || img.height == 0) {
    return null;
  }
  if (img.parent == null) {
    img.parent=parent;
  }
  Texture tex=new Texture(this,img.pixelWidth,img.pixelHeight,params);
  setCache(img,tex);
  return tex;
}
