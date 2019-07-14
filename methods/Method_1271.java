private @Nullable Drawable maybeCreateDrawableFromFactories(@Nullable ImmutableList<DrawableFactory> drawableFactories,CloseableImage closeableImage){
  if (drawableFactories == null) {
    return null;
  }
  for (  DrawableFactory factory : drawableFactories) {
    if (factory.supportsImageType(closeableImage)) {
      Drawable drawable=factory.createDrawable(closeableImage);
      if (drawable != null) {
        return drawable;
      }
    }
  }
  return null;
}
