@UiThread private void doOnFinish(){
  if (mOptimizeDisplay) {
    AnimationSet set=new AnimationSet(true);
    AlphaAnimation animation=new AlphaAnimation(1,0);
    animation.setDuration(500);
    animation.setFillAfter(true);
    set.addAnimation(animation);
    if (mThumbnailView != null) {
      mThumbnailView.setAnimation(set);
    }
    if (mProgressIndicatorView != null) {
      mProgressIndicatorView.setAnimation(set);
    }
    if (mProgressIndicator != null) {
      mProgressIndicator.onFinish();
    }
    animation.setAnimationListener(new Animation.AnimationListener(){
      @Override public void onAnimationStart(      Animation animation){
      }
      @Override public void onAnimationEnd(      Animation animation){
        if (mThumbnailView != null) {
          mThumbnailView.setVisibility(GONE);
        }
        if (mProgressIndicatorView != null) {
          mProgressIndicatorView.setVisibility(GONE);
        }
        if (mThumbnailView != null || mProgressIndicatorView != null) {
          post(new Runnable(){
            @Override public void run(){
              clearThumbnailAndProgressIndicator();
            }
          }
);
        }
      }
      @Override public void onAnimationRepeat(      Animation animation){
      }
    }
);
  }
 else {
    if (mProgressIndicator != null) {
      mProgressIndicator.onFinish();
    }
    clearThumbnailAndProgressIndicator();
  }
}
