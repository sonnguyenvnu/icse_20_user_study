private void hide(final View view){
  ViewPropertyAnimator animator=view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(300);
  animator.setListener(new Animator.AnimatorListener(){
    @Override public void onAnimationStart(    Animator animator){
      isAnimate=true;
    }
    @Override public void onAnimationEnd(    Animator animator){
      view.setVisibility(View.INVISIBLE);
      isAnimate=false;
    }
    @Override public void onAnimationCancel(    Animator animator){
      show(view);
    }
    @Override public void onAnimationRepeat(    Animator animator){
    }
  }
);
  animator.start();
}
