private void validate(){
  if (mOverlays != null) {
    for (    Drawable overlay : mOverlays) {
      Preconditions.checkNotNull(overlay);
    }
  }
}
