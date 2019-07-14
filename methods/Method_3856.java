private View findLastPartiallyOrCompletelyInvisibleChild(){
  return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1,-1);
}
