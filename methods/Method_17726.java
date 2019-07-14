@Override public void start(Resolver resolver){
  super.start(resolver);
  final View target=resolver.getAnimatedPropertyNode(mPropertyAnimation.getPropertyHandle()).getSingleTargetView();
  final float finalValue=mPropertyAnimation.getTargetValue();
  if (AnimationsDebug.ENABLED) {
    Log.d(TAG,"Trying to start, target=" + target + ", finalValue=" + finalValue);
  }
  if (target == null) {
    Log.e(TAG,"Couldn't resolve target for RT animation. Most possible reasons:\n" + "\t1) the components is not wrapped in view, please consider calling .wrapInView()\n" + "\t2) incremental mount is enabled and the view is out of screen at this moment");
    return;
  }
  mRunningAnimator=createAnimator(target,mPropertyAnimation.getProperty(),finalValue);
  mRunningAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      mRunningAnimator=null;
    }
  }
);
  mRunningAnimator.setInterpolator(mInterpolator);
  mRunningAnimator.setDuration(mDurationMs);
  mRunningAnimator.start();
}
