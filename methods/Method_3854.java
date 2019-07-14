private View findPartiallyOrCompletelyInvisibleChildClosestToStart(){
  return mShouldReverseLayout ? findLastPartiallyOrCompletelyInvisibleChild() : findFirstPartiallyOrCompletelyInvisibleChild();
}
