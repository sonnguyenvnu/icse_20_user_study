public static Drawable getDrawableSelector(int normalColor,int pressedColor){
  return new RippleDrawable(ColorStateList.valueOf(pressedColor),getRippleMask(normalColor),getRippleMask(normalColor));
}
