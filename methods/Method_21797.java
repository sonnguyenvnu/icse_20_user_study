@TargetApi(Build.VERSION_CODES.LOLLIPOP) private static Drawable generateRippleDrawable(final int color,Rect bounds){
  ColorStateList list=ColorStateList.valueOf(color);
  Drawable mask=generateCircleDrawable(Color.WHITE);
  RippleDrawable rippleDrawable=new RippleDrawable(list,null,mask);
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
    rippleDrawable.setBounds(bounds);
  }
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) {
    int center=(bounds.left + bounds.right) / 2;
    rippleDrawable.setHotspotBounds(center,bounds.top,center,bounds.bottom);
  }
  return rippleDrawable;
}
