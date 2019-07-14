@TargetApi(Build.VERSION_CODES.KITKAT_WATCH) public WindowInsets dispatchApplyWindowInsets(WindowInsets insets){
  mInsets=insets;
  ViewGroup viewGroup=mDelegate.getOwner();
  int layoutDirection=ViewCompat.getLayoutDirection(viewGroup);
  for (int i=0, count=viewGroup.getChildCount(); i < count; ++i) {
    View child=viewGroup.getChildAt(i);
    dispatchInsetsToChild(layoutDirection,child,child.getLayoutParams());
  }
  return insets.consumeSystemWindowInsets();
}
