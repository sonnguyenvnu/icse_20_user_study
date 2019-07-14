public static Point2D.Double translateCoordinates(Point2D.Double p,double min_x,double max_x,double min_y,double max_y,int dim_x,int dim_y,double l,AffineTransform t){
  double x=p.x;
  double y=p.y;
  double relative_x=x - min_x;
  double range_x=max_x - min_x;
  if (dim_x == ScatterplotFacet.LOG) {
    x=Math.log10(relative_x + 1) * l / Math.log10(range_x + 1);
  }
 else {
    x=relative_x * l / range_x;
  }
  double relative_y=y - min_y;
  double range_y=max_y - min_y;
  if (dim_y == ScatterplotFacet.LOG) {
    y=Math.log10(relative_y + 1) * l / Math.log10(range_y + 1);
  }
 else {
    y=relative_y * l / range_y;
  }
  p.x=x;
  p.y=y;
  if (t != null) {
    t.transform(p,p);
  }
  return p;
}
