public void line(double x1,double y1,double x2,double y2){
  if (x1 == x2) {
    vline(x1,y1,y2);
  }
 else   if (y1 == y2) {
    hline(y1,x1,x2);
  }
 else {
    System.err.println("warning line");
  }
}
