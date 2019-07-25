public void reset(float x,float y,float vx){
  point.x=x;
  point.y=y;
  super.reset(point,vx,0,vRotate,collisionRadius);
  length=0;
}
