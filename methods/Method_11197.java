public void setRadius(int radius){
  if (radius >= 0) {
    this.radius=radius;
  }
  drawBackgroundProgress();
  drawPrimaryProgress();
  drawSecondaryProgress();
}
