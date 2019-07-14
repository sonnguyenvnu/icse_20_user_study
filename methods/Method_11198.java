public void setMax(float max){
  if (max >= 0) {
    this.max=max;
  }
  if (this.progress > max) {
    this.progress=max;
  }
  drawPrimaryProgress();
  drawSecondaryProgress();
}
