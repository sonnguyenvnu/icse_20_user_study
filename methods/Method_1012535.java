void drag(int xx,int yy){
  xx=sim.snapGrid(xx);
  yy=sim.snapGrid(yy);
  if (abs(x - xx) < abs(y - yy))   xx=x;
 else   yy=y;
  int q1=abs(x - xx) + abs(y - yy);
  int q2=(q1 / 2) % sim.gridSize;
  if (q2 != 0)   return;
  x2=xx;
  y2=yy;
  setPoints();
}
