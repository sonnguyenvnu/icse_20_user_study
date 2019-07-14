public ColorPickerDialogBuilder showColorPreview(boolean showPreview){
  isPreviewEnabled=showPreview;
  if (!showPreview) {
    pickerCount=1;
  }
  return this;
}
