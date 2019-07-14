@SuppressLint("NewApi") private void dispatchChildInsets(View child,Object insets,int drawerGravity){
  WindowInsets wi=(WindowInsets)insets;
  if (drawerGravity == Gravity.LEFT) {
    wi=wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(),wi.getSystemWindowInsetTop(),0,wi.getSystemWindowInsetBottom());
  }
 else   if (drawerGravity == Gravity.RIGHT) {
    wi=wi.replaceSystemWindowInsets(0,wi.getSystemWindowInsetTop(),wi.getSystemWindowInsetRight(),wi.getSystemWindowInsetBottom());
  }
  child.dispatchApplyWindowInsets(wi);
}
