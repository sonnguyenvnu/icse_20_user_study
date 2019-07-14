@GuardedBy("this") private boolean isPendingLayoutCompatible(){
synchronized (mCurrentCalculateLayoutRunnableLock) {
    if (mCurrentCalculateLayoutRunnable != null) {
      return true;
    }
  }
  if (mPendingLayoutWidthSpec == SIZE_UNINITIALIZED || mPendingLayoutHeightSpec == SIZE_UNINITIALIZED) {
    return false;
  }
  return MeasureComparisonUtils.areMeasureSpecsEquivalent(mWidthSpec,mPendingLayoutWidthSpec) && MeasureComparisonUtils.areMeasureSpecsEquivalent(mHeightSpec,mPendingLayoutHeightSpec);
}
