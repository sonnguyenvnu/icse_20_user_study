public ColorPickerDialogBuilder initialColors(int[] initialColor){
  for (int i=0; i < initialColor.length && i < this.initialColor.length; i++) {
    this.initialColor[i]=initialColor[i];
  }
  return this;
}
