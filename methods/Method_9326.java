public void setPage(int page,boolean animated,Bundle params,boolean back){
  if (page == 3 || page == 8) {
    doneItem.setVisibility(View.GONE);
  }
 else {
    if (page == 0) {
      checkPermissions=true;
      checkShowPermissions=true;
    }
    doneItem.setVisibility(View.VISIBLE);
  }
  if (animated) {
    final SlideView outView=views[currentViewNum];
    final SlideView newView=views[page];
    currentViewNum=page;
    actionBar.setBackButtonImage(newView.needBackButton() || newAccount ? R.drawable.ic_ab_back : 0);
    newView.setParams(params,false);
    actionBar.setTitle(newView.getHeaderName());
    setParentActivityTitle(newView.getHeaderName());
    newView.onShow();
    newView.setX(back ? -AndroidUtilities.displaySize.x : AndroidUtilities.displaySize.x);
    newView.setVisibility(View.VISIBLE);
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        outView.setVisibility(View.GONE);
        outView.setX(0);
      }
    }
);
    animatorSet.playTogether(ObjectAnimator.ofFloat(outView,View.TRANSLATION_X,back ? AndroidUtilities.displaySize.x : -AndroidUtilities.displaySize.x),ObjectAnimator.ofFloat(newView,View.TRANSLATION_X,0));
    animatorSet.setDuration(300);
    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
    animatorSet.start();
  }
 else {
    actionBar.setBackButtonImage(views[page].needBackButton() || newAccount ? R.drawable.ic_ab_back : 0);
    views[currentViewNum].setVisibility(View.GONE);
    currentViewNum=page;
    views[page].setParams(params,false);
    views[page].setVisibility(View.VISIBLE);
    actionBar.setTitle(views[page].getHeaderName());
    setParentActivityTitle(views[page].getHeaderName());
    views[page].onShow();
  }
}
