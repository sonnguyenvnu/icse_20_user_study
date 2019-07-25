public void merge(StyleTableProperties tableProperties){
  if (tableProperties.getAlignment() != Element.ALIGN_UNDEFINED) {
    alignment=tableProperties.getAlignment();
  }
  if (tableProperties.getBackgroundColor() != null) {
    backgroundColor=tableProperties.getBackgroundColor();
  }
  if (tableProperties.getMargin() != null) {
    margin=tableProperties.getMargin();
  }
  if (tableProperties.getMarginBottom() != null) {
    marginBottom=tableProperties.getMarginBottom();
  }
  if (tableProperties.getMarginLeft() != null) {
    marginLeft=tableProperties.getMarginLeft();
  }
  if (tableProperties.getMarginRight() != null) {
    marginRight=tableProperties.getMarginRight();
  }
  if (tableProperties.getMarginTop() != null) {
    marginTop=tableProperties.getMarginTop();
  }
  if (tableProperties.getMayBreakBetweenRows() != null) {
    mayBreakBetweenRows=tableProperties.getMayBreakBetweenRows();
  }
  if (tableProperties.getWidth() != null) {
    width=tableProperties.getWidth();
  }
}
