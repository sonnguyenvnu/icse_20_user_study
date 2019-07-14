public static void fadeOut(@NonNull View view,int duration,boolean gone,@Nullable Runnable nextRunnable){
  if (view.getVisibility() != View.VISIBLE || view.getAlpha() == 0) {
    view.animate().alpha(0).setDuration(0).start();
    view.setVisibility(gone ? View.GONE : View.INVISIBLE);
    if (nextRunnable != null) {
      nextRunnable.run();
    }
    return;
  }
  view.animate().alpha(0).setDuration(duration).setInterpolator(new FastOutLinearInInterpolator()).setListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationCancel(    @NonNull Animator animator){
      mCanceled=true;
    }
    @Override public void onAnimationEnd(    @NonNull Animator animator){
      if (!mCanceled) {
        view.setVisibility(gone ? View.GONE : View.INVISIBLE);
        if (nextRunnable != null) {
          nextRunnable.run();
        }
      }
    }
  }
).start();
}
