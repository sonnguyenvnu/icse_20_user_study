public void reset(CropAreaView areaView){
  matrix.reset();
  x=0.0f;
  y=0.0f;
  rotation=0.0f;
  minimumScale=areaView.getCropWidth() / width;
  scale=minimumScale;
  matrix.postScale(scale,scale);
}
