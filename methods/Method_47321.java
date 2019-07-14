public static void crossfadeInverse(final View buttons,final View pathbar){
  pathbar.setAlpha(0f);
  pathbar.setVisibility(View.VISIBLE);
  pathbar.animate().alpha(1f).setDuration(500).setListener(null);
  buttons.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      buttons.setVisibility(View.GONE);
    }
  }
);
}
