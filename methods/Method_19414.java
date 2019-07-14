private static void detachLithoViewIfNeeded(LithoView view){
  if (view == null) {
    return;
  }
  final boolean isAttachedToWindow=view.getWindowToken() != null;
  if (isAttachedToWindow) {
    view.onStartTemporaryDetach();
  }
}
