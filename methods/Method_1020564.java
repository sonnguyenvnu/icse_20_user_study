public BasicTexture draw(BasicTexture initialTexture,GLCanvas glCanvas){
  if (this.initialTexture == initialTexture && outputTexture != null) {
    return outputTexture;
  }
  this.initialTexture=initialTexture;
  createTextures(initialTexture);
  BasicTexture drawTexture=initialTexture;
  for (int i=0, size=rawTextureList.size(); i < size; i++) {
    RawTexture rawTexture=rawTextureList.get(i);
    TextureFilter textureFilter=mMergedFilters.get(i);
    glCanvas.beginRenderTarget(rawTexture);
    glCanvas.drawTexture(drawTexture,0,0,drawTexture.getWidth(),drawTexture.getHeight(),textureFilter,null);
    glCanvas.endRenderTarget();
    drawTexture=rawTexture;
  }
  outputTexture=drawTexture;
  return drawTexture;
}
