boolean between(Point a,Point b){
  return collinearWith(a,b) && x <= max(a.x,b.x) && x >= min(a.x,b.x) && y <= max(a.y,b.y) && y >= min(a.y,b.y);
}
