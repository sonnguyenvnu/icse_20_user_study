protected void initTexture(PGraphicsOpenGL pg,PFont font){
  lastTex=-1;
  int spow=PGL.nextPowerOfTwo(font.getSize());
  minSize=PApplet.min(PGraphicsOpenGL.maxTextureSize,PApplet.max(PGL.MIN_FONT_TEX_SIZE,spow));
  maxSize=PApplet.min(PGraphicsOpenGL.maxTextureSize,PApplet.max(PGL.MAX_FONT_TEX_SIZE,2 * spow));
  if (maxSize < spow) {
    PGraphics.showWarning("The font size is too large to be properly " + "displayed with OpenGL");
  }
  addTexture(pg);
  offsetX=0;
  offsetY=0;
  lineHeight=0;
  texinfoMap=new HashMap<PFont.Glyph,TextureInfo>();
  glyphTexinfos=new TextureInfo[font.getGlyphCount()];
  addAllGlyphsToTexture(pg,font);
}
