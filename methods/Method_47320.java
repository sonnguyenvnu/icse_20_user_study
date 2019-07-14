public static void revealShow(final View view,boolean reveal){
  if (reveal) {
    ObjectAnimator animator=ObjectAnimator.ofFloat(view,View.ALPHA,0f,1f);
    animator.setDuration(300);
    animator.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationStart(      Animator animation){
        view.setVisibility(View.VISIBLE);
      }
    }
);
    animator.start();
  }
 else {
    ObjectAnimator animator=ObjectAnimator.ofFloat(view,View.ALPHA,1f,0f);
    animator.setDuration(300);
    animator.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        view.setVisibility(View.GONE);
      }
    }
);
    animator.start();
  }
}
