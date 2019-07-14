@Override public void onBackendChanged(final AnimationBackend backend){
  mAnimatedDrawable.setAnimationBackend(backend);
  mAnimationControlsManager.updateBackendData(backend);
  mAnimatedDrawable.invalidateSelf();
}
