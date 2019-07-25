public void hide(@Nullable final OnVisibilityChangedListener listener){
  if (isOpen()) {
    close();
    ViewCompat.animate(mMainFab).rotation(0).setDuration(0).start();
  }
  mMainFab.hide(new OnVisibilityChangedListener(){
    @Override public void onShown(    FloatingActionButton fab){
      super.onShown(fab);
      if (listener != null) {
        listener.onShown(fab);
      }
    }
    @Override public void onHidden(    FloatingActionButton fab){
      super.onHidden(fab);
      setVisibility(INVISIBLE);
      if (listener != null) {
        listener.onHidden(fab);
      }
    }
  }
);
}
