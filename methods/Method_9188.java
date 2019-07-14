public void enterFullscreen(){
  if (inFullscreen) {
    return;
  }
  inFullscreen=true;
  updateInlineButton();
  updateFullscreenState(false);
}
