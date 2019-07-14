/** 
 * Sets a new overlay image at the specified index. This method will throw if the given index is out of bounds.
 * @param drawable background image
 */
public void setOverlayImage(int index,@Nullable Drawable drawable){
  Preconditions.checkArgument(index >= 0 && OVERLAY_IMAGES_INDEX + index < mFadeDrawable.getNumberOfLayers(),"The given index does not correspond to an overlay image.");
  setChildDrawableAtIndex(OVERLAY_IMAGES_INDEX + index,drawable);
}
