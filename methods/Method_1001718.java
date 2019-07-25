static public Point2D middle(Line2D.Double seg){
  return new Point2D.Double((seg.x1 + seg.x2) / 2,(seg.y1 + seg.y2) / 2);
}
