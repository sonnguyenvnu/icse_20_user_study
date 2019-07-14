private void setValueInner(float value){
  if (mUsingRenderThread) {
    return;
  }
  for (int i=0, size=mMountContentGroup.size(); i < size; i++) {
    final Object mountContent=resolveReference(mMountContentGroup.getAt(i));
    if (mountContent != null) {
      mAnimatedProperty.set(mountContent,value);
    }
  }
}
