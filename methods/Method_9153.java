public static Drawable getThemedDrawable(Context context,int resId,String key){
  Drawable drawable=context.getResources().getDrawable(resId).mutate();
  drawable.setColorFilter(new PorterDuffColorFilter(getColor(key),PorterDuff.Mode.MULTIPLY));
  return drawable;
}
