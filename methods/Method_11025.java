public void setSelectedColor(int previewNumber){
  if (initialColors == null || initialColors.length < previewNumber) {
    return;
  }
  this.colorSelection=previewNumber;
  setHighlightedColor(previewNumber);
  Integer color=initialColors[previewNumber];
  if (color == null) {
    return;
  }
  setColor(color,true);
}
