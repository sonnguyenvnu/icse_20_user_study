void drag(int xx,int yy){
  xx=sim.snapGrid(xx);
  yy=sim.snapGrid(yy);
  int w1=max(sim.gridSize,abs(yy - y));
  int w2=max(sim.gridSize,abs(xx - x));
  if (w1 > w2) {
    xx=x;
    width=w2;
  }
 else {
    yy=y;
    width=w1;
  }
  x2=xx;
  y2=yy;
  setPoints();
}
