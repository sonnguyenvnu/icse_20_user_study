public void setPage(int page,boolean animated,Bundle params,boolean back){
  if (page == 3 || page == 0) {
    if (page == 0) {
    }
    doneButton.setVisibility(View.GONE);
  }
 else {
    doneButton.setVisibility(View.VISIBLE);
  }
  final SlideView outView=views[currentViewNum];
  final SlideView newView=views[page];
  currentViewNum=page;
  newView.setParams(params,false);
  actionBar.setTitle(newView.getHeaderName());
  newView.onShow();
  newView.setX(back ? -AndroidUtilities.displaySize.x : AndroidUtilities.displaySize.x);
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
  animatorSet.setDuration(300);
  animatorSet.playTogether(ObjectAnimator.ofFloat(outView,"translationX",back ? AndroidUtilities.displaySize.x : -AndroidUtilities.displaySize.x),ObjectAnimator.ofFloat(newView,"translationX",0));
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      newView.setVisibility(View.VISIBLE);
    }
    @Override public void onAnimationEnd(    Animator animation){
      outView.setVisibility(View.GONE);
      outView.setX(0);
    }
  }
);
  animatorSet.start();
}
