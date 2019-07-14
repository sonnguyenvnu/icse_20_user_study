private void configureBoundsIfUnderlyingChanged(){
  boolean scaleTypeChanged=false;
  if (mScaleType instanceof StatefulScaleType) {
    Object state=((StatefulScaleType)mScaleType).getState();
    scaleTypeChanged=(state == null || !state.equals(mScaleTypeState));
    mScaleTypeState=state;
  }
  boolean underlyingChanged=mUnderlyingWidth != getCurrent().getIntrinsicWidth() || mUnderlyingHeight != getCurrent().getIntrinsicHeight();
  if (underlyingChanged || scaleTypeChanged) {
    configureBounds();
  }
}
