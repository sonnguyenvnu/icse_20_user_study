public static void setCombinedDrawableColor(Drawable combinedDrawable,int color,boolean isIcon){
  if (!(combinedDrawable instanceof CombinedDrawable)) {
    return;
  }
  Drawable drawable;
  if (isIcon) {
    drawable=((CombinedDrawable)combinedDrawable).getIcon();
  }
 else {
    drawable=((CombinedDrawable)combinedDrawable).getBackground();
  }
  if (drawable instanceof ColorDrawable) {
    ((ColorDrawable)drawable).setColor(color);
  }
 else {
    drawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
  }
}
