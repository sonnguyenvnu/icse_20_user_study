public void start(){
  mBezierPath.reset();
  mState=RUNNING;
  mBezierPointList=BezierUtils.buildBezierPoint(mControlPointList,FRAME);
  prepareBezierPath();
  if (mIsShowReduceOrderLine) {
    BezierUtils.calculateIntermediateLine(mIntermediateList,mControlPointList,FRAME);
  }
  mCurRatio=0;
  setCurBezierPoint(mBezierPointList.get(0));
  invalidate();
}
