@Override public void onScrollChanged(boolean reachedTop,int scroll){
  if (AppHelper.isDeviceAnimationEnabled(getContext())) {
    if (getPresenter().isRepo() && appBarLayout != null && bottomNavigation != null && webView != null) {
      boolean shouldExpand=webView.getScrollY() == 0;
      if (!isAppBarMoving && shouldExpand != isAppBarExpanded) {
        isAppBarMoving=true;
        isAppBarExpanded=shouldExpand;
        bottomNavigation.setExpanded(shouldExpand,true);
        appBarLayout.setExpanded(shouldExpand,true);
        webView.setNestedScrollingEnabled(shouldExpand);
        if (shouldExpand)         webView.onTouchEvent(MotionEvent.obtain(0,0,MotionEvent.ACTION_UP,0,0,0));
      }
    }
  }
}
