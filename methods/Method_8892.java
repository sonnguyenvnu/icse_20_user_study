private void setCurrentSwatch(Swatch swatch,boolean updateInterface){
  renderView.setColor(swatch.color);
  renderView.setBrushSize(swatch.brushWeight);
  if (updateInterface) {
    colorPicker.setSwatch(swatch);
  }
  if (currentEntityView instanceof TextPaintView) {
    ((TextPaintView)currentEntityView).setSwatch(swatch);
  }
}
