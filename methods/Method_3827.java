@Override public PointF computeScrollVectorForPosition(int targetPosition){
  if (getChildCount() == 0) {
    return null;
  }
  final int firstChildPos=getPosition(getChildAt(0));
  final int direction=targetPosition < firstChildPos != mShouldReverseLayout ? -1 : 1;
  if (mOrientation == HORIZONTAL) {
    return new PointF(direction,0);
  }
 else {
    return new PointF(0,direction);
  }
}
