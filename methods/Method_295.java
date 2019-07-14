@UiThread public static Drawable getTintedDrawable(Context context,@DrawableRes int id,@AttrRes int tintAttrId){
  boolean attributeFound=context.getTheme().resolveAttribute(tintAttrId,VALUE,true);
  if (!attributeFound) {
    throw new Resources.NotFoundException("Required tint color attribute with name " + context.getResources().getResourceEntryName(tintAttrId) + " and attribute ID " + tintAttrId + " was not found.");
  }
  Drawable drawable=ContextCompat.getDrawable(context,id);
  drawable=DrawableCompat.wrap(drawable.mutate());
  int color=ContextCompat.getColor(context,VALUE.resourceId);
  DrawableCompat.setTint(drawable,color);
  return drawable;
}
