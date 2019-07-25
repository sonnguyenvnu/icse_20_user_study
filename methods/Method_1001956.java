public void scale(float factor){
  Iterator it=getPointsIterator();
  while (it.hasNext()) {
    ShapePoint point=(ShapePoint)it.next();
    point.x*=factor;
    point.y*=factor;
  }
}
