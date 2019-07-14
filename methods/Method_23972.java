protected Texture wrapFrontTexture(Texture texture){
  if (texture == null) {
    texture=new Texture(graphics);
    texture.init(graphics.width,graphics.height,glColorTex.get(frontTex),TEXTURE_2D,RGBA,fboWidth,fboHeight,NEAREST,NEAREST,CLAMP_TO_EDGE,CLAMP_TO_EDGE);
    texture.invertedY(true);
    texture.colorBuffer(true);
  }
 else {
    texture.glName=glColorTex.get(frontTex);
  }
  return texture;
}
