private void updateTransform(){
  Matrix matrix=new Matrix();
  float scale=painting != null ? getWidth() / painting.getSize().width : 1.0f;
  if (scale <= 0) {
    scale=1.0f;
  }
  Size paintingSize=getPainting().getSize();
  matrix.preTranslate(getWidth() / 2.0f,getHeight() / 2.0f);
  matrix.preScale(scale,-scale);
  matrix.preTranslate(-paintingSize.width / 2.0f,-paintingSize.height / 2.0f);
  input.setMatrix(matrix);
  float proj[]=GLMatrix.LoadOrtho(0.0f,internal.bufferWidth,0.0f,internal.bufferHeight,-1.0f,1.0f);
  float effectiveProjection[]=GLMatrix.LoadGraphicsMatrix(matrix);
  float finalProjection[]=GLMatrix.MultiplyMat4f(proj,effectiveProjection);
  painting.setRenderProjection(finalProjection);
}
