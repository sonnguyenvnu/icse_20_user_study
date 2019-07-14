@SuppressLint("NewApi") private void applyMarginInsets(MarginLayoutParams lp,Object insets,int drawerGravity,boolean topOnly){
  WindowInsets wi=(WindowInsets)insets;
  if (drawerGravity == Gravity.LEFT) {
    wi=wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(),wi.getSystemWindowInsetTop(),0,wi.getSystemWindowInsetBottom());
  }
 else   if (drawerGravity == Gravity.RIGHT) {
    wi=wi.replaceSystemWindowInsets(0,wi.getSystemWindowInsetTop(),wi.getSystemWindowInsetRight(),wi.getSystemWindowInsetBottom());
  }
  lp.leftMargin=wi.getSystemWindowInsetLeft();
  lp.topMargin=topOnly ? 0 : wi.getSystemWindowInsetTop();
  lp.rightMargin=wi.getSystemWindowInsetRight();
  lp.bottomMargin=wi.getSystemWindowInsetBottom();
}
