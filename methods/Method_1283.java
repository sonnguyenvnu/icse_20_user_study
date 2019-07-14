public PipelineDraweeController newController(){
  PipelineDraweeController controller=internalCreateController(mResources,mDeferredReleaser,mAnimatedDrawableFactory,mUiThreadExecutor,mMemoryCache,mDrawableFactories);
  if (mDebugOverlayEnabledSupplier != null) {
    controller.setDrawDebugOverlay(mDebugOverlayEnabledSupplier.get());
  }
  return controller;
}
