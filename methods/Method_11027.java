public void setLightness(float lightness){
  int lastSelectedColor=getSelectedColor();
  this.lightness=lightness;
  this.initialColor=Color.HSVToColor(RxImageTool.alphaValueAsInt(this.alpha),currentColorCircle.getHsvWithLightness(lightness));
  if (this.colorEdit != null) {
    this.colorEdit.setText(RxImageTool.getHexString(this.initialColor,this.alphaSlider != null));
  }
  if (this.alphaSlider != null && this.initialColor != null) {
    this.alphaSlider.setColor(this.initialColor);
  }
  callOnColorChangedListeners(lastSelectedColor,this.initialColor);
  updateColorWheel();
  invalidate();
}
