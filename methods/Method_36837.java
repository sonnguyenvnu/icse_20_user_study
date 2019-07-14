@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  GridLayoutHelper helper=new GridLayoutHelper(1);
  helper.setItemCount(getCells().size());
  if (style != null && !Float.isNaN(style.aspectRatio)) {
    helper.setAspectRatio(style.aspectRatio);
  }
  return helper;
}
