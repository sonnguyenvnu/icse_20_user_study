@Override public void beginDraw(){
  g2=checkImage();
  if (strokeObject != null) {
    g2.setStroke(strokeObject);
  }
  if (fontObject != null) {
    g2.setFont(fontObject);
  }
  if (blendMode != 0) {
    blendMode(blendMode);
  }
  handleSmooth();
  checkSettings();
  resetMatrix();
  vertexCount=0;
}
