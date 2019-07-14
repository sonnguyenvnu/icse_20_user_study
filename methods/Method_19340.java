@Override @UiThread public void setViewportChangedListener(@Nullable ViewportChanged viewportChangedListener){
  mViewportManager.addViewportChangedListener(viewportChangedListener);
}
