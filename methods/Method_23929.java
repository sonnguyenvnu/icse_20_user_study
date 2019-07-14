protected void addToTexture(PGraphicsOpenGL pg,int idx,PFont.Glyph glyph){
  int w=1 + glyph.width + 1;
  int h=1 + glyph.height + 1;
  int[] rgba=new int[w * h];
  int t=0;
  int p=0;
  if (PGL.BIG_ENDIAN) {
    java.util.Arrays.fill(rgba,0,w,0xFFFFFF00);
    t=w;
    for (int y=0; y < glyph.height; y++) {
      rgba[t++]=0xFFFFFF00;
      for (int x=0; x < glyph.width; x++) {
        rgba[t++]=0xFFFFFF00 | glyph.image.pixels[p++];
      }
      rgba[t++]=0xFFFFFF00;
    }
    java.util.Arrays.fill(rgba,(h - 1) * w,h * w,0xFFFFFF00);
  }
 else {
    java.util.Arrays.fill(rgba,0,w,0x00FFFFFF);
    t=w;
    for (int y=0; y < glyph.height; y++) {
      rgba[t++]=0x00FFFFFF;
      for (int x=0; x < glyph.width; x++) {
        rgba[t++]=(glyph.image.pixels[p++] << 24) | 0x00FFFFFF;
      }
      rgba[t++]=0x00FFFFFF;
    }
    java.util.Arrays.fill(rgba,(h - 1) * w,h * w,0x00FFFFFF);
  }
  if (offsetX + w > textures[lastTex].glWidth) {
    offsetX=0;
    offsetY+=lineHeight;
  }
  lineHeight=Math.max(lineHeight,h);
  boolean resized=false;
  if (offsetY + lineHeight > textures[lastTex].glHeight) {
    resized=addTexture(pg);
    if (resized) {
      updateGlyphsTexCoords();
    }
 else {
      offsetX=0;
      offsetY=0;
      lineHeight=0;
    }
  }
  TextureInfo tinfo=new TextureInfo(lastTex,offsetX,offsetY,w,h,rgba);
  offsetX+=w;
  if (idx == glyphTexinfos.length) {
    TextureInfo[] temp=new TextureInfo[glyphTexinfos.length + 1];
    System.arraycopy(glyphTexinfos,0,temp,0,glyphTexinfos.length);
    glyphTexinfos=temp;
  }
  glyphTexinfos[idx]=tinfo;
  texinfoMap.put(glyph,tinfo);
}
