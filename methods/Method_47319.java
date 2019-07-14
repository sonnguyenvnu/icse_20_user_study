public static void crossfade(View buttons,final View pathbar){
  buttons.setAlpha(0f);
  buttons.setVisibility(View.VISIBLE);
  buttons.animate().alpha(1f).setDuration(100).setListener(null);
  pathbar.animate().alpha(0f).setDuration(100).setListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      pathbar.setVisibility(View.GONE);
    }
  }
);
}
