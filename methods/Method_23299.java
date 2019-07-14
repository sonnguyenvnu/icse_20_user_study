/** 
 * Create a new glyph, and add the character to the current font.
 * @param c character to create an image for.
 */
protected void addGlyph(char c){
  Glyph glyph=new Glyph(c);
  if (glyphCount == glyphs.length) {
    glyphs=(Glyph[])PApplet.expand(glyphs);
  }
  if (glyphCount == 0) {
    glyph.index=0;
    glyphs[glyphCount]=glyph;
    if (glyph.value < 128) {
      ascii[glyph.value]=0;
    }
  }
 else   if (glyphs[glyphCount - 1].value < glyph.value) {
    glyphs[glyphCount]=glyph;
    if (glyph.value < 128) {
      ascii[glyph.value]=glyphCount;
    }
  }
 else {
    for (int i=0; i < glyphCount; i++) {
      if (glyphs[i].value > c) {
        for (int j=glyphCount; j > i; --j) {
          glyphs[j]=glyphs[j - 1];
          if (glyphs[j].value < 128) {
            ascii[glyphs[j].value]=j;
          }
        }
        glyph.index=i;
        glyphs[i]=glyph;
        if (c < 128)         ascii[c]=i;
        break;
      }
    }
  }
  glyphCount++;
}
