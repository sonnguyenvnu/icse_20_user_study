protected Texture wrapBackTexture(Texture texture){
  if (texture == null) {
    texture=new Texture(graphics);
    texture.init(graphics.width,graphics.height,glColorTex.get(backTex),TEXTURE_2D,RGBA,fboWidth,fboHeight,NEAREST,NEAREST,CLAMP_TO_EDGE,CLAMP_TO_EDGE);
    texture.invertedY(true);
    texture.colorBuffer(true);
    graphics.setCache(graphics,texture);
  }
 else {
    texture.glName=glColorTex.get(backTex);
  }
  return texture;
}
