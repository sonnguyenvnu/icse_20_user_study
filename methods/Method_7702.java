public static Drawable createSelectorDrawable(int color,int maskType){
  Drawable drawable;
  if (Build.VERSION.SDK_INT >= 21) {
    Drawable maskDrawable=null;
    if (maskType == 1 && Build.VERSION.SDK_INT >= 23) {
      maskDrawable=null;
    }
 else     if (maskType == 1 || maskType == 3 || maskType == 4) {
      maskPaint.setColor(0xffffffff);
      maskDrawable=new Drawable(){
        @Override public void draw(        Canvas canvas){
          android.graphics.Rect bounds=getBounds();
          int rad;
          if (maskType == 1) {
            rad=AndroidUtilities.dp(20);
          }
 else           if (maskType == 3) {
            rad=(Math.max(bounds.width(),bounds.height()) / 2);
          }
 else {
            rad=(int)Math.ceil(Math.sqrt((bounds.left - bounds.centerX()) * (bounds.left - bounds.centerX()) + (bounds.top - bounds.centerY()) * (bounds.top - bounds.centerY())));
          }
          canvas.drawCircle(bounds.centerX(),bounds.centerY(),rad,maskPaint);
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
 else     if (maskType == 2) {
      maskDrawable=new ColorDrawable(0xffffffff);
    }
    ColorStateList colorStateList=new ColorStateList(new int[][]{StateSet.WILD_CARD},new int[]{color});
    RippleDrawable rippleDrawable=new RippleDrawable(colorStateList,null,maskDrawable);
    if (maskType == 1 && Build.VERSION.SDK_INT >= 23) {
      rippleDrawable.setRadius(AndroidUtilities.dp(20));
    }
    return rippleDrawable;
  }
 else {
    StateListDrawable stateListDrawable=new StateListDrawable();
    stateListDrawable.addState(new int[]{android.R.attr.state_pressed},new ColorDrawable(color));
    stateListDrawable.addState(new int[]{android.R.attr.state_selected},new ColorDrawable(color));
    stateListDrawable.addState(StateSet.WILD_CARD,new ColorDrawable(0x00000000));
    return stateListDrawable;
  }
}
