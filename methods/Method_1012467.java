void drag(int xx,int yy){
  yy=sim.snapGrid(yy);
  if (xx < x) {
    xx=x;
    yy=y;
  }
 else {
    y=y2=yy;
    x2=sim.snapGrid(xx);
  }
  setPoints();
}
