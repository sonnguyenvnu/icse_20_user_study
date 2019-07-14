@TargetApi(Build.VERSION_CODES.KITKAT_WATCH) private void dispatchInsetsToChild(int layoutDirection,View child,ViewGroup.LayoutParams childLayoutParams){
  int childGravity=GravityCompat.getAbsoluteGravity(mDelegate.getGravityFromLayoutParams(childLayoutParams),layoutDirection);
  int childInsetLeft=mInsets.getSystemWindowInsetLeft();
  int childInsetRight=mInsets.getSystemWindowInsetRight();
  if (childLayoutParams.width != FrameLayout.LayoutParams.MATCH_PARENT) {
    if ((childGravity & (Gravity.AXIS_PULL_BEFORE << Gravity.AXIS_X_SHIFT)) == 0) {
      childInsetLeft=0;
    }
    if ((childGravity & (Gravity.AXIS_PULL_AFTER << Gravity.AXIS_X_SHIFT)) == 0) {
      childInsetRight=0;
    }
  }
  int childInsetTop=mInsets.getSystemWindowInsetTop();
  int childInsetBottom=mInsets.getSystemWindowInsetBottom();
  if (childLayoutParams.height != FrameLayout.LayoutParams.MATCH_PARENT) {
    if ((childGravity & (Gravity.AXIS_PULL_BEFORE << Gravity.AXIS_Y_SHIFT)) == 0) {
      childInsetTop=0;
    }
    if ((childGravity & (Gravity.AXIS_PULL_AFTER << Gravity.AXIS_Y_SHIFT)) == 0) {
      childInsetBottom=0;
    }
  }
  WindowInsets childInsets=mInsets.replaceSystemWindowInsets(childInsetLeft,childInsetTop,childInsetRight,childInsetBottom);
  child.dispatchApplyWindowInsets(childInsets);
}
