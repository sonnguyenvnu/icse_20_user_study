/** 
 * Sets a single overlay.
 * @param overlay overlay drawable
 * @return modified instance of this builder
 */
public GenericDraweeHierarchyBuilder setOverlay(@Nullable Drawable overlay){
  if (overlay == null) {
    mOverlays=null;
  }
 else {
    mOverlays=Arrays.asList(overlay);
  }
  return this;
}
