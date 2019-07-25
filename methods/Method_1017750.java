boolean contains(Point p){
  Point extreme=new Point(Integer.MAX_VALUE,p.y);
  Segment pToExtreme=new Segment(p,extreme);
  int count=0;
  int i=0;
  do {
    int next=(i + 1) % points.size();
    Segment iToNext=new Segment(points.get(i),points.get(next));
    if (iToNext.intersects(pToExtreme)) {
      if (orientation(points.get(i),p,points.get(next)) == COLLINEAR)       return p.between(points.get(i),points.get(next));
      ++count;
    }
    i=next;
  }
 while (i != 0);
  return count % 2 != 0;
}
