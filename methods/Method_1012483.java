void drag(int xx,int yy){
  xx=sim.snapGrid(xx);
  yy=sim.snapGrid(yy);
  if (xx == x)   yy=y;
  x2=xx;
  y2=yy;
  setPoints();
}
