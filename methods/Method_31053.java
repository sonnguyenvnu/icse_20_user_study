@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupTransitionBeforeDecorate(Activity activity){
  if (!shouldEnableTransition()) {
    return;
  }
  Window window=activity.getWindow();
  window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
  window.setSharedElementsUseOverlay(false);
}
