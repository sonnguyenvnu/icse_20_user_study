private void removeAllCallbacks(){
  if (mChangeCurrentByOneFromLongPressCommand != null) {
    removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
  }
  mPressedStateHelper.cancel();
}
