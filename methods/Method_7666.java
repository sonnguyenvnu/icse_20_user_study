public void closeDrawer(boolean fast){
  cancelCurrentAnimation();
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(this,"drawerPosition",0));
  animatorSet.setInterpolator(new DecelerateInterpolator());
  if (fast) {
    animatorSet.setDuration(Math.max((int)(200.0f / drawerLayout.getMeasuredWidth() * drawerPosition),50));
  }
 else {
    animatorSet.setDuration(300);
  }
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animator){
      onDrawerAnimationEnd(false);
    }
  }
);
  animatorSet.start();
}
