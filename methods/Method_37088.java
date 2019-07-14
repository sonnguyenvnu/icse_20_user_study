private void resetViews(RecyclerView recyclerView,final int swipingType,final boolean reachActionEdge,final int direction){
  if (enableAnim) {
    int contentWidth=recyclerView.getWidth();
    AnimatorSet animatorSet=new AnimatorSet();
    List<Animator> list=new ArrayList<>();
    String translation="translationX";
    if (swipingType == SWIPING_VER) {
      translation="translationY";
    }
    for (    View view : mChildList) {
      ObjectAnimator animator;
      if (reachActionEdge) {
        animator=ObjectAnimator.ofFloat(view,translation,contentWidth * direction).setDuration(ANIMATE_DURATION);
        animator.setInterpolator(new AccelerateInterpolator());
      }
 else {
        animator=ObjectAnimator.ofFloat(view,translation,0).setDuration(ANIMATE_DURATION);
        animator.setInterpolator(new DecelerateInterpolator());
      }
      list.add(animator);
    }
    animatorSet.playTogether(list);
    animatorSet.addListener(new Animator.AnimatorListener(){
      @Override public void onAnimationStart(      Animator animation){
      }
      @Override public void onAnimationEnd(      Animator animation){
        if (swipingType == SWIPING_HOR && reachActionEdge) {
          if (mSwipeCardRef != null && mSwipeCardRef.get() != null) {
            SwipeCard swipeCard=mSwipeCardRef.get();
            swipeCard.switchTo(swipeCard.getCurrentIndex() - direction);
          }
        }
        mChildList.clear();
      }
      @Override public void onAnimationCancel(      Animator animation){
      }
      @Override public void onAnimationRepeat(      Animator animation){
      }
    }
);
    animatorSet.start();
  }
 else {
    if (swipingType == SWIPING_HOR && reachActionEdge) {
      if (mSwipeCardRef != null && mSwipeCardRef.get() != null) {
        SwipeCard swipeCard=mSwipeCardRef.get();
        swipeCard.switchTo(swipeCard.getCurrentIndex() - direction);
      }
    }
    mChildList.clear();
  }
  if (swipingType == SWIPING_VER) {
    if (pullFromEndListener != null) {
      if (mDistanceY < 0 && (mDistanceY < -pullFromEndListener.getPullEdge())) {
        pullFromEndListener.onAction();
      }
 else {
        pullFromEndListener.onReset();
      }
    }
  }
  swipeType=SWIPING_NONE;
}
