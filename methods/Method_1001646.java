private Line2D.Double change(Line2D.Double line,Point2D p1,Point2D p2){
  if (line.getP1().equals(p1) == false && line.getP2().equals(p2) == false) {
    return line;
  }
  final double dx=line.x2 - line.x1;
  final double dy=line.y2 - line.y1;
  if (line.getP1().equals(p1)) {
    p1=new Point2D.Double(line.x1 + dx / 10,line.y1 + dy / 10);
  }
 else {
    p1=line.getP1();
  }
  if (line.getP2().equals(p2)) {
    p2=new Point2D.Double(line.x2 - dx / 10,line.y2 - dy / 10);
  }
 else {
    p2=line.getP2();
  }
  return new Line2D.Double(p1,p2);
}
