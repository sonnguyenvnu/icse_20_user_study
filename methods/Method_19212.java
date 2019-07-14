@GuardedBy("this") private void acquireAnimationState(){
  if (mComponentTree == null) {
    return;
  }
  mHasMounted=mComponentTree.hasMounted();
}
