private void maybeShowController(boolean isForced){
  if (!useController || player == null) {
    return;
  }
  boolean wasShowingIndefinitely=controller.isVisible() && controller.getShowTimeoutMs() <= 0;
  boolean shouldShowIndefinitely=shouldShowControllerIndefinitely();
  if (isForced || wasShowingIndefinitely || shouldShowIndefinitely) {
    controller.show();
  }
}
