@Override public void start(Resolver resolver){
  if (mHasStarted) {
    throw new RuntimeException("Starting binding multiple times");
  }
  mHasStarted=true;
  mResolver=resolver;
  if (!shouldStart()) {
    notifyCanceledBeforeStart();
    return;
  }
  notifyWillStart();
  mIsActive=true;
  mBinding.prepareToStartLater();
  final AnimationBindingListener bindingListener=new AnimationBindingListener(){
    @Override public void onScheduledToStartLater(    AnimationBinding binding){
    }
    @Override public void onWillStart(    AnimationBinding binding){
    }
    @Override public void onFinish(    AnimationBinding binding){
      binding.removeListener(this);
      DelayBinding.this.finish();
    }
    @Override public void onCanceledBeforeStart(    AnimationBinding binding){
      onFinish(binding);
    }
    @Override public boolean shouldStart(    AnimationBinding binding){
      return true;
    }
  }
;
  mBinding.addListener(bindingListener);
  final ChoreographerCompat.FrameCallback delayedStartCallback=new ChoreographerCompat.FrameCallback(){
    @Override public void doFrame(    long frameTimeNanos){
      if (!mIsActive) {
        return;
      }
      mBinding.start(mResolver);
    }
  }
;
  ChoreographerCompatImpl.getInstance().postFrameCallbackDelayed(delayedStartCallback,mDelayMs);
}
