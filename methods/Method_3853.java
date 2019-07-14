private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(){
  return mShouldReverseLayout ? findFirstPartiallyOrCompletelyInvisibleChild() : findLastPartiallyOrCompletelyInvisibleChild();
}
