private void drawSearch(Canvas canvas){
  mPaint.setColor(Color.WHITE);
  canvas.translate(mViewWidth / 2,mViewHeight / 2);
  canvas.drawColor(Color.parseColor("#0082D7"));
switch (mCurrentState) {
case NONE:
    canvas.drawPath(path_srarch,mPaint);
  break;
case STARTING:
mMeasure.setPath(path_srarch,false);
Path dst=new Path();
mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue,mMeasure.getLength(),dst,true);
canvas.drawPath(dst,mPaint);
break;
case SEARCHING:
mMeasure.setPath(path_circle,false);
Path dst2=new Path();
float stop=mMeasure.getLength() * mAnimatorValue;
float start=(float)(stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
mMeasure.getSegment(start,stop,dst2,true);
canvas.drawPath(dst2,mPaint);
break;
case ENDING:
mMeasure.setPath(path_srarch,false);
Path dst3=new Path();
mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue,mMeasure.getLength(),dst3,true);
canvas.drawPath(dst3,mPaint);
break;
}
}
