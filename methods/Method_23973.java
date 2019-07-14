protected void bindFrontTexture(){
  usingFrontTex=true;
  if (!texturingIsEnabled(TEXTURE_2D)) {
    enableTexturing(TEXTURE_2D);
  }
  bindTexture(TEXTURE_2D,glColorTex.get(frontTex));
}
