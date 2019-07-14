private boolean onTouchMove(float x,float y){
  float scale=((View)getParent()).getScaleX();
  Point translation=new Point((x - previousLocationX) / scale,(y - previousLocationY) / scale);
  float distance=(float)Math.hypot(translation.x,translation.y);
  float minDistance=hasPanned ? 6 : 16;
  if (distance > minDistance) {
    pan(translation);
    previousLocationX=x;
    previousLocationY=y;
    hasPanned=true;
    return true;
  }
  return false;
}
