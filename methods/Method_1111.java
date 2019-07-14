/** 
 * Sets various paint properties on the drawable
 * @param drawable Drawable on which to set the properties
 * @param properties wrapper around mValue values to set on the drawable
 */
public static void setDrawableProperties(@Nullable Drawable drawable,@Nullable DrawableProperties properties){
  if (drawable == null || properties == null) {
    return;
  }
  properties.applyTo(drawable);
}
