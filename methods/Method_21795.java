private static Drawable generateBackground(int color,int fadeTime,Rect bounds){
  StateListDrawable drawable=new StateListDrawable();
  drawable.setExitFadeDuration(fadeTime);
  drawable.addState(new int[]{android.R.attr.state_checked},generateCircleDrawable(color));
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    drawable.addState(new int[]{android.R.attr.state_pressed},generateRippleDrawable(color,bounds));
  }
 else {
    drawable.addState(new int[]{android.R.attr.state_pressed},generateCircleDrawable(color));
  }
  drawable.addState(new int[]{},generateCircleDrawable(Color.TRANSPARENT));
  return drawable;
}
