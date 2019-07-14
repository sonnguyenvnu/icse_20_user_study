public static void fadeIn(@NonNull View view,int duration){
  if (view.getVisibility() == View.VISIBLE && view.getAlpha() == 1) {
    view.animate().alpha(1).setDuration(0).start();
    return;
  }
  view.setAlpha(isVisible(view) ? view.getAlpha() : 0);
  view.setVisibility(View.VISIBLE);
  view.animate().alpha(1).setDuration(duration).setInterpolator(new FastOutSlowInInterpolator()).setListener(null).start();
}
