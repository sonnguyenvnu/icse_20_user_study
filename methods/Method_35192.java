private View findDeepestChild(ViewGroup viewGroup){
  if (viewGroup.getChildCount() == 0) {
    return viewGroup;
  }
  View lastChild=viewGroup.getChildAt(viewGroup.getChildCount() - 1);
  if (lastChild instanceof ViewGroup) {
    return findDeepestChild((ViewGroup)lastChild);
  }
 else {
    return lastChild;
  }
}
