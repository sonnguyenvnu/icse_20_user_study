@Override public void onAreaChange(){
  areaView.setGridType(CropAreaView.GridType.MAJOR,false);
  float x=previousAreaRect.centerX() - areaView.getCropCenterX();
  float y=previousAreaRect.centerY() - areaView.getCropCenterY();
  state.translate(x,y);
  updateMatrix();
  areaView.getCropRect(previousAreaRect);
  fitContentInBounds(true,false,false);
}
