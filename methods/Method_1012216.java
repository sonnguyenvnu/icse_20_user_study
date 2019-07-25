private void show(final View view){
  ViewPropertyAnimator animator=view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(300);
  animator.setListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animator){
      view.setVisibility(View.VISIBLE);
      isAnimate=true;
    }
    @Override public void onAnimationEnd(    Animator animator){
      isAnimate=false;
    }
    @Override public void onAnimationCancel(    Animator animator){
      hide(view);
    }
    @Override public void onAnimationRepeat(    Animator animator){
    }
  }
);
  animator.start();
}
