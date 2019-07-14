private void maybeOverrideVisibilityHandling(){
  if (mLegacyVisibilityHandlingEnabled) {
    Drawable drawable=getDrawable();
    if (drawable != null) {
      drawable.setVisible(getVisibility() == VISIBLE,false);
    }
  }
}
