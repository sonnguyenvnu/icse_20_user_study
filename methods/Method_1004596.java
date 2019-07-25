@Override public void add(final long pX,final long pY){
  if (mFirst) {
    mFirst=false;
    mPath.moveTo(pX,pY);
    mLatestPoint.set(pX,pY);
  }
 else   if (mLatestPoint.x != pX || mLatestPoint.y != pY) {
    mPath.lineTo(pX,pY);
    mLatestPoint.set(pX,pY);
  }
}
