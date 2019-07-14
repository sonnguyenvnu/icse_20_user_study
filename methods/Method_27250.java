public static void tintDrawable(@NonNull Drawable drawable,@ColorInt int color){
  drawable.mutate().setColorFilter(color,PorterDuff.Mode.SRC_IN);
}
