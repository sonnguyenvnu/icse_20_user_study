public void show(){
  if (!isVisible()) {
    setVisibility(VISIBLE);
    if (visibilityListener != null) {
      visibilityListener.onVisibilityChange(getVisibility());
    }
    updateAll();
  }
  hideAfterTimeout();
}
