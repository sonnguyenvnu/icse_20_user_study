private static Drawable getTintedDrawable(Context context,int drawableRes,int color){
  Drawable drawable;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    drawable=context.getResources().getDrawable(drawableRes,null);
    if (drawable != null) {
      drawable.setTint(color);
    }
  }
 else {
    drawable=context.getResources().getDrawable(drawableRes);
    if (drawable != null) {
      drawable.setColorFilter(color,PorterDuff.Mode.SRC_ATOP);
    }
  }
  return drawable;
}
