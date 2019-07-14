@UiThread public static void mimicFabVisibility(boolean show,@NonNull View view,@Nullable FloatingActionButton.OnVisibilityChangedListener listener){
  if (show) {
    view.animate().cancel();
    if (ViewCompat.isLaidOut(view)) {
      if (view.getVisibility() != View.VISIBLE) {
        view.setAlpha(0f);
        view.setScaleY(0f);
        view.setScaleX(0f);
      }
      view.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200).setInterpolator(LINEAR_OUT_SLOW_IN_INTERPOLATOR).withStartAction(() -> {
        view.setVisibility(View.VISIBLE);
        if (listener != null)         listener.onShown(null);
      }
);
    }
 else {
      view.setVisibility(View.VISIBLE);
      view.setAlpha(1f);
      view.setScaleY(1f);
      view.setScaleX(1f);
      if (listener != null)       listener.onShown(null);
    }
  }
 else {
    view.animate().scaleX(0f).scaleY(0f).alpha(0f).setDuration(40).setInterpolator(FAST_OUT_LINEAR_IN_INTERPOLATOR);
    view.setVisibility(View.GONE);
    if (listener != null)     listener.onHidden(null);
  }
}
