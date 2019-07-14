@Override protected void textCharImpl(char ch,float x,float y){
  PFont.Glyph glyph=textFont.getGlyph(ch);
  if (glyph != null) {
    if (textMode == MODEL) {
      FontTexture.TextureInfo tinfo=textTex.getTexInfo(glyph);
      if (tinfo == null) {
        tinfo=textTex.addToTexture(this,glyph);
      }
      float high=glyph.height / (float)textFont.getSize();
      float bwidth=glyph.width / (float)textFont.getSize();
      float lextent=glyph.leftExtent / (float)textFont.getSize();
      float textent=glyph.topExtent / (float)textFont.getSize();
      float x1=x + lextent * textSize;
      float y1=y - textent * textSize;
      float x2=x1 + bwidth * textSize;
      float y2=y1 + high * textSize;
      textCharModelImpl(tinfo,x1,y1,x2,y2);
    }
 else     if (textMode == SHAPE) {
      textCharShapeImpl(ch,x,y);
    }
  }
}
