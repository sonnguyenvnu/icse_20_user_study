void calcRangeTicks(){
  double dx=actual_maxx - actual_minx;
  double dy=actual_maxy - actual_miny;
  int sw=getWidth();
  int sh=getHeight();
  double border=1.09345;
  if (Math.abs(last_minx - actual_minx) + Math.abs(last_maxx - actual_maxx) > 0.1 * (actual_maxx - actual_minx)) {
    mTickX=(float)calcTick(sw,dx);
    dx=mTickX * Math.ceil(border * dx / mTickX);
    double tx=(actual_minx + actual_maxx - dx) / 2;
    tx=mTickX * Math.floor(tx / mTickX);
    minx=(float)tx;
    tx=(actual_minx + actual_maxx + dx) / 2;
    tx=mTickX * Math.ceil(tx / mTickX);
    maxx=(float)tx;
    last_minx=actual_minx;
    last_maxx=actual_maxx;
  }
  if (Math.abs(last_miny - actual_miny) + Math.abs(last_maxy - actual_maxy) > 0.1 * (actual_maxy - actual_miny)) {
    mTickY=(float)calcTick(sh,dy);
    dy=mTickY * Math.ceil(border * dy / mTickY);
    double ty=(actual_miny + actual_maxy - dy) / 2;
    ty=mTickY * Math.floor(ty / mTickY);
    miny=(float)ty;
    ty=(actual_miny + actual_maxy + dy) / 2;
    ty=mTickY * Math.ceil(ty / mTickY);
    maxy=(float)ty;
    last_miny=actual_miny;
    last_maxy=actual_maxy;
  }
}
