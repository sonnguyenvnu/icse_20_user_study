private StateListDrawable createFillDrawable(float strokeWidth){
  StateListDrawable drawable=new StateListDrawable();
  drawable.addState(new int[]{-android.R.attr.state_enabled},createCircleDrawable(mColorDisabled,strokeWidth));
  drawable.addState(new int[]{android.R.attr.state_pressed},createCircleDrawable(mColorPressed,strokeWidth));
  drawable.addState(new int[]{},createCircleDrawable(mColorNormal,strokeWidth));
  return drawable;
}
