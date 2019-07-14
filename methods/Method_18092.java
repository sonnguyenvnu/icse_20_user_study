/** 
 * @deprecated use {@link #foreground(ComparableDrawable)} more efficient diffing of drawables.
 */
@Deprecated @Override public InternalNode foreground(@Nullable Drawable foreground){
  return foreground(foreground != null ? DefaultComparableDrawable.create(foreground) : null);
}
