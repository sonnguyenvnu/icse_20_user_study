@Override public float calculateValue(long frameTimeNanos){
  boolean hasInput=hasInput();
  final Object mountContent=resolveReference(mMountContentGroup.getMostSignificantUnit());
  if (mountContent == null) {
    if (hasInput) {
      return getInput().getValue();
    }
    return getValue();
  }
  if (!hasInput) {
    return mAnimatedProperty.get(mountContent);
  }
  final float value=getInput().getValue();
  setValueInner(value);
  return value;
}
