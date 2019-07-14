private void viewGlyphDetail(SVGGlyph glyph){
  try {
    glyphDetailViewer.setGlyph(SVGGlyphLoader.getIcoMoonGlyph(fileName + "." + glyph.getName()));
  }
 catch (  Exception e) {
  }
}
