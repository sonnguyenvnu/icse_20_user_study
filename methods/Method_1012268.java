private void invalidate(){
  if (useFeature()) {
    View owner=mOwner.get();
    if (owner == null) {
      return;
    }
    if (mShadowElevation == 0) {
      owner.setElevation(0);
    }
 else {
      owner.setElevation(mShadowElevation);
    }
    owner.invalidateOutline();
  }
}
