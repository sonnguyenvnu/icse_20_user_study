public @Nullable Drawable setImage(@Nullable Drawable imageDrawable,@Nullable CloseableReference<CloseableImage> imageReference){
  CloseableReference.closeSafely(mImageReference);
  mImageReference=CloseableReference.cloneOrNull(imageReference);
  return setDrawable(IMAGE_DRAWABLE_INDEX,imageDrawable);
}
