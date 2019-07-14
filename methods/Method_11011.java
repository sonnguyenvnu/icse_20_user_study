public ColorPickerDialogBuilder setPickerCount(int pickerCount) throws IndexOutOfBoundsException {
  if (pickerCount < 1 || pickerCount > 5) {
    throw new IndexOutOfBoundsException("Picker Can Only Support 1-5 Colors");
  }
  this.pickerCount=pickerCount;
  if (this.pickerCount > 1) {
    this.isPreviewEnabled=true;
  }
  return this;
}
