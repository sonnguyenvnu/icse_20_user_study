@Override @Nullable public Drawable create(FrescoState frescoState){
  if (!mDebugOverlayEnabled.get()) {
    return null;
  }
  DebugOverlayDrawable drawable=new DebugOverlayDrawable();
  drawable.addDebugData("URI","" + frescoState.getUri());
  return drawable;
}
