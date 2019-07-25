void drag(int xx,int yy){
  xx=sim.snapGrid(xx);
  yy=sim.snapGrid(yy);
  if (noDiagonal) {
    if (Math.abs(x - xx) < Math.abs(y - yy)) {
      xx=x;
    }
 else {
      yy=y;
    }
  }
  x2=xx;
  y2=yy;
  setPoints();
}
