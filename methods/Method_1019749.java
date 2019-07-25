public void merge(StyleParagraphProperties paragraphProperties){
  if (paragraphProperties.getAlignment() != Element.ALIGN_UNDEFINED) {
    alignment=paragraphProperties.getAlignment();
  }
  if (paragraphProperties.getAutoTextIndent() != null) {
    autoTextIndent=paragraphProperties.getAutoTextIndent();
  }
  if (paragraphProperties.getBackgroundColor() != null) {
    backgroundColor=paragraphProperties.getBackgroundColor();
  }
  if (paragraphProperties.getBorder() != null) {
    border=paragraphProperties.getBorder();
  }
  if (paragraphProperties.getBorderBottom() != null) {
    borderBottom=paragraphProperties.getBorderBottom();
  }
  if (paragraphProperties.getBorderLeft() != null) {
    borderLeft=paragraphProperties.getBorderLeft();
  }
  if (paragraphProperties.getBorderRight() != null) {
    borderRight=paragraphProperties.getBorderRight();
  }
  if (paragraphProperties.getBorderTop() != null) {
    borderTop=paragraphProperties.getBorderTop();
  }
  if (paragraphProperties.getBreakBefore() != null) {
    breakBefore=paragraphProperties.getBreakBefore();
  }
  if (paragraphProperties.getJoinBorder() != null) {
    joinBorder=paragraphProperties.getJoinBorder();
  }
  if (paragraphProperties.getKeepTogether() != null) {
    keepTogether=paragraphProperties.getKeepTogether();
  }
  if (paragraphProperties.getLineHeight() != null) {
    lineHeight=paragraphProperties.getLineHeight();
  }
  if (paragraphProperties.getMargin() != null) {
    margin=paragraphProperties.getMargin();
  }
  if (paragraphProperties.getMarginBottom() != null) {
    marginBottom=paragraphProperties.getMarginBottom();
  }
  if (paragraphProperties.getMarginLeft() != null) {
    marginLeft=paragraphProperties.getMarginLeft();
  }
  if (paragraphProperties.getMarginRight() != null) {
    marginRight=paragraphProperties.getMarginRight();
  }
  if (paragraphProperties.getMarginTop() != null) {
    marginTop=paragraphProperties.getMarginTop();
  }
  if (paragraphProperties.getPadding() != null) {
    padding=paragraphProperties.getPadding();
  }
  if (paragraphProperties.getPaddingBottom() != null) {
    paddingBottom=paragraphProperties.getPaddingBottom();
  }
  if (paragraphProperties.getPaddingLeft() != null) {
    paddingLeft=paragraphProperties.getPaddingLeft();
  }
  if (paragraphProperties.getPaddingRight() != null) {
    paddingRight=paragraphProperties.getPaddingRight();
  }
  if (paragraphProperties.getPaddingTop() != null) {
    paddingTop=paragraphProperties.getPaddingTop();
  }
  if (paragraphProperties.getTextIndent() != null) {
    textIndent=paragraphProperties.getTextIndent();
  }
}
