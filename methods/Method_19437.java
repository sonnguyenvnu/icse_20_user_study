private boolean isClickCloseToSpan(ClickableSpan span,Spanned buffer,Layout layout,Region touchAreaRegion,Region clipBoundsRegion){
  final Region clickableSpanAreaRegion=new Region();
  final Path clickableSpanAreaPath=new Path();
  layout.getSelectionPath(buffer.getSpanStart(span),buffer.getSpanEnd(span),clickableSpanAreaPath);
  clickableSpanAreaRegion.setPath(clickableSpanAreaPath,clipBoundsRegion);
  return clickableSpanAreaRegion.op(touchAreaRegion,Region.Op.INTERSECT);
}
