@TargetApi(Build.VERSION_CODES.LOLLIPOP) private Drawable createFillDrawable(){
  StateListDrawable drawable=new StateListDrawable();
  drawable.addState(new int[]{-android.R.attr.state_enabled},createCircleDrawable(mColorDisabled));
  drawable.addState(new int[]{android.R.attr.state_pressed},createCircleDrawable(mColorPressed));
  drawable.addState(new int[]{},createCircleDrawable(mColorNormal));
  if (Util.hasLollipop()) {
    RippleDrawable ripple=new RippleDrawable(new ColorStateList(new int[][]{{}},new int[]{mColorRipple}),drawable,null);
    setOutlineProvider(new ViewOutlineProvider(){
      @Override public void getOutline(      View view,      Outline outline){
        outline.setOval(0,0,view.getWidth(),view.getHeight());
      }
    }
);
    setClipToOutline(true);
    mBackgroundDrawable=ripple;
    return ripple;
  }
  mBackgroundDrawable=drawable;
  return drawable;
}
