public void curveto(double x1,double y1,double x2,double y2,double x3,double y3){
  append(format(x1) + " " + format(y1) + " " + format(x2) + " " + format(y2) + " " + format(x3) + " " + format(y3) + " curveto",true);
  ensureVisible(x1,y1);
  ensureVisible(x2,y2);
  ensureVisible(x3,y3);
}
