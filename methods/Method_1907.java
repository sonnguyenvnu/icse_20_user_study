private void updateBitmapFrameCache(BitmapFrameCache bitmapFrameCache){
  mActiveFrameNumber=-1;
  mBitmapAnimationBackend=ExampleBitmapAnimationFactory.createColorBitmapAnimationBackend(SampleData.COLORS,300,bitmapFrameCache);
  AnimationBackend backendWithInactivityCheck=AnimationBackendUtils.wrapAnimationBackendWithInactivityCheck(getContext(),mBitmapAnimationBackend);
  setupFrameInformationContainer(mBitmapAnimationBackend);
  mAnimationControlsManager.updateBackendData(backendWithInactivityCheck);
  mAnimatedDrawable.setAnimationBackend(backendWithInactivityCheck);
  mAnimatedDrawable.invalidateSelf();
}
