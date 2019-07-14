@Override protected void applyAnimatedFraction(@NonNull PointF holder,float fraction){
  if (fraction < 0) {
    fraction=0;
  }
  if (fraction > 1) {
    fraction=1;
  }
  mPathMeasure.getPosTan(fraction * mPathLength,mTempArray,null);
  holder.set(mTempArray[0],mTempArray[1]);
}
