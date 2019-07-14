public void setDrawRipple(boolean value){
  if (Build.VERSION.SDK_INT < 21 || value == drawRipple) {
    return;
  }
  drawRipple=value;
  if (rippleDrawable == null) {
    ripplePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    ripplePaint.setColor(0xffffffff);
    Drawable maskDrawable;
    if (Build.VERSION.SDK_INT >= 23) {
      maskDrawable=null;
    }
 else {
      maskDrawable=new Drawable(){
        @Override public void draw(        Canvas canvas){
          android.graphics.Rect bounds=getBounds();
          canvas.drawCircle(bounds.centerX(),bounds.centerY(),AndroidUtilities.dp(18),ripplePaint);
        }
        @Override public void setAlpha(        int alpha){
        }
        @Override public void setColorFilter(        ColorFilter colorFilter){
        }
        @Override public int getOpacity(){
          return PixelFormat.UNKNOWN;
        }
      }
;
    }
    ColorStateList colorStateList=new ColorStateList(new int[][]{StateSet.WILD_CARD},new int[]{0});
    rippleDrawable=new RippleDrawable(colorStateList,null,maskDrawable);
    if (Build.VERSION.SDK_INT >= 23) {
      rippleDrawable.setRadius(AndroidUtilities.dp(18));
    }
    rippleDrawable.setCallback(this);
  }
  if (isChecked && colorSet != 2 || !isChecked && colorSet != 1) {
    int color=isChecked ? Theme.getColor(Theme.key_switchTrackBlueSelectorChecked) : Theme.getColor(Theme.key_switchTrackBlueSelector);
    ColorStateList colorStateList=new ColorStateList(new int[][]{StateSet.WILD_CARD},new int[]{color});
    rippleDrawable.setColor(colorStateList);
    colorSet=isChecked ? 2 : 1;
  }
  if (Build.VERSION.SDK_INT >= 28 && value) {
    rippleDrawable.setHotspot(isChecked ? 0 : AndroidUtilities.dp(100),AndroidUtilities.dp(18));
  }
  rippleDrawable.setState(value ? pressedState : StateSet.NOTHING);
  invalidate();
}
