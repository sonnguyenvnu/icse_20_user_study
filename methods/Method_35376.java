@Override public void onDestroyActionMode(ActionMode mode){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    window.setStatusBarColor(statusBarColor);
    if (actionModeInAnimator != null) {
      actionModeInAnimator.cancel();
    }
    if (actionModeOutAnimator != null) {
      actionModeOutAnimator.start();
    }
    ViewUtils.clearLightStatusBar(window.getDecorView());
  }
  isShowing=false;
  if (actionListener != null) {
    actionListener.onDismissAction();
  }
}
