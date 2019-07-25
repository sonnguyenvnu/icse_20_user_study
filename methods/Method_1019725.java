public void merge(StyleTableCellProperties tableCellProperties){
  if (tableCellProperties.getBackgroundColor() != null) {
    backgroundColor=tableCellProperties.getBackgroundColor();
  }
  if (tableCellProperties.getBorder() != null) {
    border=tableCellProperties.getBorder();
  }
  if (tableCellProperties.getBorderBottom() != null) {
    borderBottom=tableCellProperties.getBorderBottom();
  }
  if (tableCellProperties.getBorderLeft() != null) {
    borderLeft=tableCellProperties.getBorderLeft();
  }
  if (tableCellProperties.getBorderRight() != null) {
    borderRight=tableCellProperties.getBorderRight();
  }
  if (tableCellProperties.getBorderTop() != null) {
    borderTop=tableCellProperties.getBorderTop();
  }
  if (tableCellProperties.getPadding() != null) {
    padding=tableCellProperties.getPadding();
  }
  if (tableCellProperties.getPaddingBottom() != null) {
    paddingBottom=tableCellProperties.getPaddingBottom();
  }
  if (tableCellProperties.getPaddingLeft() != null) {
    paddingLeft=tableCellProperties.getPaddingLeft();
  }
  if (tableCellProperties.getPaddingRight() != null) {
    paddingRight=tableCellProperties.getPaddingRight();
  }
  if (tableCellProperties.getPaddingTop() != null) {
    paddingTop=tableCellProperties.getPaddingTop();
  }
  if (tableCellProperties.getVerticalAlignment() != Element.ALIGN_UNDEFINED) {
    verticalAlignment=tableCellProperties.getVerticalAlignment();
  }
}
