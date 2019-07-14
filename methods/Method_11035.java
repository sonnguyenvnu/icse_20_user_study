public void setColor(int color){
  this.color=color;
  this.value=RxImageTool.getAlphaPercent(color);
  if (bar != null) {
    updateBar();
    invalidate();
  }
}
