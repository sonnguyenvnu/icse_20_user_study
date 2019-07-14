private StateListDrawable createDefaultOverButtonBgDrawable(){
  int dp12=(int)ThemeUtils.applyDimensionDp(this,12.f);
  int dp8=(int)ThemeUtils.applyDimensionDp(this,8.f);
  float dp4=ThemeUtils.applyDimensionDp(this,4.f);
  float[] round=new float[]{dp4,dp4,dp4,dp4,dp4,dp4,dp4,dp4};
  ShapeDrawable pressedDrawable=new ShapeDrawable(new RoundRectShape(round,null,null));
  pressedDrawable.setPadding(dp12,dp8,dp12,dp8);
  int pressedColor=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_over_button_pressed_color,R.color.gallery_default_toolbar_over_button_pressed_color);
  pressedDrawable.getPaint().setColor(pressedColor);
  int normalColor=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_over_button_normal_color,R.color.gallery_default_toolbar_over_button_normal_color);
  ShapeDrawable normalDrawable=new ShapeDrawable(new RoundRectShape(round,null,null));
  normalDrawable.setPadding(dp12,dp8,dp12,dp8);
  normalDrawable.getPaint().setColor(normalColor);
  StateListDrawable stateListDrawable=new StateListDrawable();
  stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pressedDrawable);
  stateListDrawable.addState(new int[]{},normalDrawable);
  return stateListDrawable;
}
