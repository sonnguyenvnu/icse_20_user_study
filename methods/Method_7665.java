public void openDrawer(boolean fast){
  if (!allowOpenDrawer) {
    return;
  }
  if (AndroidUtilities.isTablet() && parentActionBarLayout != null && parentActionBarLayout.parentActivity != null) {
    AndroidUtilities.hideKeyboard(parentActionBarLayout.parentActivity.getCurrentFocus());
  }
  cancelCurrentAnimation();
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(this,"drawerPosition",drawerLayout.getMeasuredWidth()));
  animatorSet.setInterpolator(new DecelerateInterpolator());
  if (fast) {
    animatorSet.setDuration(Math.max((int)(200.0f / drawerLayout.getMeasuredWidth() * (drawerLayout.getMeasuredWidth() - drawerPosition)),50));
  }
 else {
    animatorSet.setDuration(300);
  }
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animator){
      onDrawerAnimationEnd(true);
    }
  }
);
  animatorSet.start();
  currentAnimation=animatorSet;
}
