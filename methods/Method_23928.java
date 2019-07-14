public TextureInfo addToTexture(PGraphicsOpenGL pg,PFont.Glyph glyph){
  int n=glyphTexinfos.length;
  if (n == 0) {
    glyphTexinfos=new TextureInfo[1];
  }
  addToTexture(pg,n,glyph);
  return glyphTexinfos[n];
}
