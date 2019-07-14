private double[] circleFrom3Points(Point2D a,Point2D b,Point2D c){
  double ax, ay, bx, by, cx, cy, x1, y11, dx1, dy1, x2, y2, dx2, dy2, ox, oy, dx, dy, radius;
  ax=a.getX();
  ay=a.getY();
  bx=b.getX();
  by=b.getY();
  cx=c.getX();
  cy=c.getY();
  x1=(bx + ax) / 2;
  y11=(by + ay) / 2;
  dy1=bx - ax;
  dx1=-(by - ay);
  x2=(cx + bx) / 2;
  y2=(cy + by) / 2;
  dy2=cx - bx;
  dx2=-(cy - by);
  ox=(y11 * dx1 * dx2 + x2 * dx1 * dy2 - x1 * dy1 * dx2 - y2 * dx1 * dx2) / (dx1 * dy2 - dy1 * dx2);
  oy=(ox - x1) * dy1 / dx1 + y11;
  dx=ox - ax;
  dy=oy - ay;
  radius=Math.sqrt(dx * dx + dy * dy);
  return new double[]{ox,oy,radius};
}
