public static Drawable createSimpleSelectorCircleDrawable(int size,int defaultColor,int pressedColor){
  OvalShape ovalShape=new OvalShape();
  ovalShape.resize(size,size);
  ShapeDrawable defaultDrawable=new ShapeDrawable(ovalShape);
  defaultDrawable.getPaint().setColor(defaultColor);
  ShapeDrawable pressedDrawable=new ShapeDrawable(ovalShape);
  if (Build.VERSION.SDK_INT >= 21) {
    pressedDrawable.getPaint().setColor(0xffffffff);
    ColorStateList colorStateList=new ColorStateList(new int[][]{StateSet.WILD_CARD},new int[]{pressedColor});
    return new RippleDrawable(colorStateList,defaultDrawable,pressedDrawable);
  }
 else {
    pressedDrawable.getPaint().setColor(pressedColor);
    StateListDrawable stateListDrawable=new StateListDrawable();
    stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pressedDrawable);
    stateListDrawable.addState(new int[]{android.R.attr.state_focused},pressedDrawable);
    stateListDrawable.addState(StateSet.WILD_CARD,defaultDrawable);
    return stateListDrawable;
  }
}
