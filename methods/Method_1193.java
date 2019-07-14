private float[] getOrCreateRoundedCornersRadii(){
  if (mCornersRadii == null) {
    mCornersRadii=new float[8];
  }
  return mCornersRadii;
}
