/** 
 * Clones the specified drawable.
 * @param drawable the drawable to clone.
 * @return a clone of the drawable or null if the drawable cannot be cloned.
 */
public static @Nullable Drawable cloneDrawable(Drawable drawable){
  if (drawable instanceof CloneableDrawable) {
    return ((CloneableDrawable)drawable).cloneDrawable();
  }
  Drawable.ConstantState constantState=drawable.getConstantState();
  return constantState != null ? constantState.newDrawable() : null;
}
