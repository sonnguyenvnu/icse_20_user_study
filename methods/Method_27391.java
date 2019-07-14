public void clear(@NonNull DrawableGetter drawableGetter){
  if (drawableGetter.cachedTargets != null) {
    for (    GlideDrawableTarget target : drawableGetter.cachedTargets) {
      Glide.clear(target);
    }
  }
}
