public void setPage(int page,boolean animated,Bundle params){
  if (page == 3) {
    doneItem.setVisibility(View.GONE);
  }
  final SlideView outView=views[currentViewNum];
  final SlideView newView=views[page];
  currentViewNum=page;
  newView.setParams(params,false);
  newView.onShow();
  if (animated) {
    newView.setTranslationX(AndroidUtilities.displaySize.x);
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
    animatorSet.setDuration(300);
    animatorSet.playTogether(ObjectAnimator.ofFloat(outView,"translationX",-AndroidUtilities.displaySize.x),ObjectAnimator.ofFloat(newView,"translationX",0));
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationStart(      Animator animation){
        newView.setVisibility(View.VISIBLE);
      }
      @Override public void onAnimationEnd(      Animator animation){
        outView.setVisibility(View.GONE);
        outView.setX(0);
      }
    }
);
    animatorSet.start();
  }
 else {
    newView.setTranslationX(0);
    newView.setVisibility(View.VISIBLE);
    if (outView != newView) {
      outView.setVisibility(View.GONE);
    }
  }
}
