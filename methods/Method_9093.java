private void consistencyCheck(){
  if (mLastLayoutParamsHashCode == UNINITIALIZED_HASH) {
    validateLayoutParams();
    mLastLayoutParamsHashCode=computeLayoutParamsHashCode();
  }
 else   if (mLastLayoutParamsHashCode != computeLayoutParamsHashCode()) {
    invalidateStructure();
    consistencyCheck();
  }
}
