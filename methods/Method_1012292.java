@TargetApi(Build.VERSION_CODES.LOLLIPOP) static void reveal(final View view,final AnimationListener listener){
  int cx=view.getWidth() - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,view.getResources().getDisplayMetrics());
  int cy=view.getHeight() / 2;
  int finalRadius=Math.max(view.getWidth(),view.getHeight());
  Animator anim=ViewAnimationUtils.createCircularReveal(view,cx,cy,0,finalRadius);
  view.setVisibility(View.VISIBLE);
  anim.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      listener.onAnimationStart(view);
    }
    @Override public void onAnimationEnd(    Animator animation){
      listener.onAnimationEnd(view);
    }
    @Override public void onAnimationCancel(    Animator animation){
      listener.onAnimationCancel(view);
    }
    @Override public void onAnimationRepeat(    Animator animation){
    }
  }
);
  anim.start();
}
