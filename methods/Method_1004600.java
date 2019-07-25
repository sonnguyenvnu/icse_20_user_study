@Override public void add(final long pX,final long pY){
  mPoint1.set(pX,pY);
  if (mFirstPoint) {
    mFirstPoint=false;
  }
 else {
    clip(mPoint0.x,mPoint0.y,mPoint1.x,mPoint1.y);
  }
  mPoint0.set(mPoint1);
}
