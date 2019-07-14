@Override protected boolean onStateChange(int[] state){
  if (mCurrentDelegate == null) {
    return super.onStateChange(state);
  }
  return mCurrentDelegate.setState(state);
}
