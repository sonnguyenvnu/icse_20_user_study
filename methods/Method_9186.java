public void exitFullscreen(){
  if (!inFullscreen) {
    return;
  }
  inFullscreen=false;
  updateInlineButton();
  updateFullscreenState(false);
}
