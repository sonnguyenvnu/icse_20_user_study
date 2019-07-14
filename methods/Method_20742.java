/** 
 * Recursive crawls the view hierarchy of `viewGroup` in order to find a clickable child and click it.
 */
private static boolean recursiveClickFirstChildView(final @NonNull ViewGroup viewGroup){
  try {
    boolean continueRecursing=true;
    for (int idx=0; idx < viewGroup.getChildCount() && continueRecursing; idx++) {
      final View child=viewGroup.getChildAt(idx);
      if (child.hasOnClickListeners()) {
        child.performClick();
        return false;
      }
 else {
        continueRecursing=recursiveClickFirstChildView((ViewGroup)child);
      }
    }
  }
 catch (  ClassCastException|NullPointerException ignored) {
  }
  return true;
}
