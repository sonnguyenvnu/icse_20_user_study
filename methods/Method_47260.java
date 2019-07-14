private ValueAnimator createMoveSelectedAnimator(final float moveTo,int was,int now,int steps){
  ValueAnimator moveSelected=ValueAnimator.ofFloat(selectedDotX,moveTo);
  retreatAnimation=new PendingRetreatAnimator(was,now,steps,now > was ? new RightwardStartPredicate(moveTo - ((moveTo - selectedDotX) * 0.25f)) : new LeftwardStartPredicate(moveTo + ((selectedDotX - moveTo) * 0.25f)));
  retreatAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      resetState();
      pageChanging=false;
    }
  }
);
  moveSelected.addUpdateListener(valueAnimator -> {
    selectedDotX=(Float)valueAnimator.getAnimatedValue();
    retreatAnimation.startIfNecessary(selectedDotX);
    postInvalidateOnAnimation();
  }
);
  moveSelected.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      selectedDotInPosition=false;
    }
    @Override public void onAnimationEnd(    Animator animation){
      selectedDotInPosition=true;
    }
  }
);
  moveSelected.setStartDelay(selectedDotInPosition ? animDuration / 4L : 0L);
  moveSelected.setDuration(animDuration * 3L / 4L);
  moveSelected.setInterpolator(interpolator);
  return moveSelected;
}
