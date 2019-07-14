public void updateLayout(){
  float w=areaView.getCropWidth();
  if (state != null) {
    areaView.calculateRect(initialAreaRect,state.getWidth() / state.getHeight());
    areaView.setActualRect(areaView.getAspectRatio());
    areaView.getCropRect(previousAreaRect);
    float ratio=areaView.getCropWidth() / w;
    state.scale(ratio,0,0);
    updateMatrix();
  }
}
