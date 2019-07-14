@Override public void setupOverlayDrawable(FrescoContext frescoContext,FrescoDrawable frescoDrawable,Resources resources,ImageOptions imageOptions,@Nullable Drawable overlayDrawable,@Nullable Drawable debugOverlayDrawable){
  if (overlayDrawable == null) {
    overlayDrawable=buildOverlayDrawable(resources,imageOptions);
  }
  if (debugOverlayDrawable != null) {
    if (overlayDrawable == null) {
      overlayDrawable=debugOverlayDrawable;
    }
 else {
      overlayDrawable=new LayerDrawable(new Drawable[]{overlayDrawable,debugOverlayDrawable});
    }
  }
  frescoDrawable.setOverlayDrawable(overlayDrawable);
  frescoDrawable.showOverlayImmediately();
}
