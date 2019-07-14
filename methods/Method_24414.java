protected boolean fragmentedGroup(PGraphicsOpenGL g){
  return g.getHint(DISABLE_OPTIMIZED_STROKE) || (textures != null && (1 < textures.size() || untexChild)) || strokedTexture;
}
