@Override public PointF computeScrollVectorForPosition(int targetPosition){
  final int direction=calculateScrollDirectionForPosition(targetPosition);
  PointF outVector=new PointF();
  if (direction == 0) {
    return null;
  }
  if (mOrientation == HORIZONTAL) {
    outVector.x=direction;
    outVector.y=0;
  }
 else {
    outVector.x=0;
    outVector.y=direction;
  }
  return outVector;
}
