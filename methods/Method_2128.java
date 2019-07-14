protected void calculateInterpolation(Matrix outMatrix,float fraction){
  for (int i=0; i < 9; i++) {
    mCurrentValues[i]=(1 - fraction) * mStartValues[i] + fraction * mStopValues[i];
  }
  outMatrix.setValues(mCurrentValues);
}
