public static Drawable getThemedDrawable(Context context,int resId,int color){
  if (context == null) {
    return null;
  }
  Drawable drawable=context.getResources().getDrawable(resId).mutate();
  drawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
  return drawable;
}
