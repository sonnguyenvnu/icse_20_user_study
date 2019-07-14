public void updateGlyphsTexCoords(){
  for (int i=0; i < glyphTexinfos.length; i++) {
    TextureInfo tinfo=glyphTexinfos[i];
    if (tinfo != null && tinfo.texIndex == lastTex) {
      tinfo.updateUV();
    }
  }
}
