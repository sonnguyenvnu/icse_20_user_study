public static Drawable createEmojiIconSelectorDrawable(Context context,int resource,int defaultColor,int pressedColor){
  Resources resources=context.getResources();
  Drawable defaultDrawable=resources.getDrawable(resource).mutate();
  if (defaultColor != 0) {
    defaultDrawable.setColorFilter(new PorterDuffColorFilter(defaultColor,PorterDuff.Mode.MULTIPLY));
  }
  Drawable pressedDrawable=resources.getDrawable(resource).mutate();
  if (pressedColor != 0) {
    pressedDrawable.setColorFilter(new PorterDuffColorFilter(pressedColor,PorterDuff.Mode.MULTIPLY));
  }
  StateListDrawable stateListDrawable=new StateListDrawable(){
    @Override public boolean selectDrawable(    int index){
      if (Build.VERSION.SDK_INT < 21) {
        Drawable drawable=getStateDrawable(this,index);
        ColorFilter colorFilter=null;
        if (drawable instanceof BitmapDrawable) {
          colorFilter=((BitmapDrawable)drawable).getPaint().getColorFilter();
        }
 else         if (drawable instanceof NinePatchDrawable) {
          colorFilter=((NinePatchDrawable)drawable).getPaint().getColorFilter();
        }
        boolean result=super.selectDrawable(index);
        if (colorFilter != null) {
          drawable.setColorFilter(colorFilter);
        }
        return result;
      }
      return super.selectDrawable(index);
    }
  }
;
  stateListDrawable.setEnterFadeDuration(1);
  stateListDrawable.setExitFadeDuration(200);
  stateListDrawable.addState(new int[]{android.R.attr.state_selected},pressedDrawable);
  stateListDrawable.addState(new int[]{},defaultDrawable);
  return stateListDrawable;
}
