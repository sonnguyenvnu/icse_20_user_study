public void manage(double x,double y){
  if (x < minX) {
    minX=x;
  }
  if (y < minY) {
    minY=y;
  }
  if (x > maxX) {
    maxX=x;
  }
  if (y > maxY) {
    maxY=y;
  }
}
