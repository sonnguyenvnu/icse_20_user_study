public static Point2D intersect(Line2D.Double orig,Shape shape){
  final Line2D.Double copy=new Line2D.Double(orig.x1,orig.y1,orig.x2,orig.y2);
  final boolean contains1=shape.contains(copy.x1,copy.y1);
  final boolean contains2=shape.contains(copy.x2,copy.y2);
  if (contains1 ^ contains2 == false) {
    throw new IllegalArgumentException();
  }
  while (true) {
    final double mx=(copy.x1 + copy.x2) / 2;
    final double my=(copy.y1 + copy.y2) / 2;
    final boolean containsMiddle=shape.contains(mx,my);
    if (contains1 == containsMiddle) {
      copy.x1=mx;
      copy.y1=my;
    }
 else {
      copy.x2=mx;
      copy.y2=my;
    }
    if (dist(copy) < 0.1) {
      if (contains1) {
        return new Point2D.Double(copy.x2,copy.y2);
      }
      if (contains2) {
        return new Point2D.Double(copy.x1,copy.y1);
      }
      throw new IllegalStateException();
    }
  }
}
