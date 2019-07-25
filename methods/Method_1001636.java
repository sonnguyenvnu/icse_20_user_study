public void manage(int x,int y){
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
