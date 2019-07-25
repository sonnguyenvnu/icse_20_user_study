void prepare(Graphics2D g){
  if (!styler.isToolTipsEnabled()) {
    return;
  }
  dataPointList.clear();
  Rectangle clipBounds=g.getClipBounds();
  leftEdge=clipBounds.getX() + MARGIN;
  rightEdge=clipBounds.getMaxX() - MARGIN * 2;
  topEdge=clipBounds.getY() + MARGIN;
  bottomEdge=clipBounds.getMaxY() - MARGIN * 2;
}
