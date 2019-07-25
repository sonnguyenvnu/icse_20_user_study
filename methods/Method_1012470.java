void move(int dx,int dy){
  x+=dx;
  y+=dy;
  x2+=dx;
  y2+=dy;
  boundingBox.translate(dx,dy);
  setPoints();
}
