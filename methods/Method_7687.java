public static Drawable createEditTextDrawable(Context context,boolean alert){
  Resources resources=context.getResources();
  Drawable defaultDrawable=resources.getDrawable(R.drawable.search_dark).mutate();
  defaultDrawable.setColorFilter(new PorterDuffColorFilter(getColor(alert ? Theme.key_dialogInputField : Theme.key_windowBackgroundWhiteInputField),PorterDuff.Mode.MULTIPLY));
  Drawable pressedDrawable=resources.getDrawable(R.drawable.search_dark_activated).mutate();
  pressedDrawable.setColorFilter(new PorterDuffColorFilter(getColor(alert ? Theme.key_dialogInputFieldActivated : Theme.key_windowBackgroundWhiteInputFieldActivated),PorterDuff.Mode.MULTIPLY));
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
  stateListDrawable.addState(new int[]{android.R.attr.state_enabled,android.R.attr.state_focused},pressedDrawable);
  stateListDrawable.addState(new int[]{android.R.attr.state_focused},pressedDrawable);
  stateListDrawable.addState(StateSet.WILD_CARD,defaultDrawable);
  return stateListDrawable;
}
