private void autoScale(){
  if (getMatrixScaleX() > 2.2) {
    zoomAnimate(getMatrixScaleX(),2.0f);
  }
 else   if (getMatrixScaleX() < 0.98) {
    zoomAnimate(getMatrixScaleX(),1.0f);
  }
}
