@GuardedBy("this") private boolean hasSizeSpec(){
  assertHoldsLock(this);
  return mWidthSpec != SIZE_UNINITIALIZED && mHeightSpec != SIZE_UNINITIALIZED;
}
