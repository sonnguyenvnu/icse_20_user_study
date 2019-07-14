@UiThread private static void animateSafeVisibility(final boolean show,@NonNull final View view,int visibility){
  view.animate().cancel();
  ViewPropertyAnimator animator=view.animate().setDuration(200).alpha(show ? 1F : 0F).setInterpolator(new AccelerateInterpolator()).setListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      super.onAnimationStart(animation);
      if (show) {
        view.setScaleX(1);
        view.setScaleY(1);
        view.setVisibility(View.VISIBLE);
      }
    }
    @Override public void onAnimationEnd(    @NonNull Animator animation){
      super.onAnimationEnd(animation);
      if (!show) {
        view.setVisibility(visibility);
        view.setScaleX(0);
        view.setScaleY(0);
      }
      animation.removeListener(this);
      view.clearAnimation();
    }
  }
);
  animator.scaleX(show ? 1 : 0).scaleY(show ? 1 : 0);
}
