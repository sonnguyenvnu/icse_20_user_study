private void setColorText(int argb){
  if (colorEdit == null) {
    return;
  }
  colorEdit.setText(RxImageTool.getHexString(argb,this.alphaSlider != null));
}
