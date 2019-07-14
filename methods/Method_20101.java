@NonNull public Drawable getIcon(Context context){
  Drawable drawable=ContextCompat.getDrawable(context,R.drawable.ic_tag_faces_black_24dp);
  if (drawable == null) {
    throw new IllegalStateException("Could not get drawable ic_tag_faces_black_24dp");
  }
  drawable=DrawableCompat.wrap(drawable);
  int color=isLocalUser ? Color.BLUE : Color.RED;
  if (Build.VERSION.SDK_INT >= 22) {
    DrawableCompat.setTint(drawable,color);
  }
 else {
    drawable.mutate().setColorFilter(color,PorterDuff.Mode.SRC_IN);
  }
  return drawable;
}
