/** 
 * Sets the  {@link Drawable} for this divider.
 * @param drawable Drawable that should be used as a divider.
 */
public void setDrawable(@NonNull Drawable drawable){
  if (drawable == null) {
    throw new IllegalArgumentException("Drawable cannot be null.");
  }
  mDivider=drawable;
}
