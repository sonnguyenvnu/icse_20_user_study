@Override protected void onBoundsChange(Rect bounds){
  if (mCurrentDelegate != null) {
    mCurrentDelegate.setBounds(bounds);
  }
}
