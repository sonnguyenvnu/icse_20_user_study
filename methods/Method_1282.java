public void init(Resources resources,DeferredReleaser deferredReleaser,DrawableFactory animatedDrawableFactory,Executor uiThreadExecutor,MemoryCache<CacheKey,CloseableImage> memoryCache,@Nullable ImmutableList<DrawableFactory> drawableFactories,@Nullable Supplier<Boolean> debugOverlayEnabledSupplier){
  mResources=resources;
  mDeferredReleaser=deferredReleaser;
  mAnimatedDrawableFactory=animatedDrawableFactory;
  mUiThreadExecutor=uiThreadExecutor;
  mMemoryCache=memoryCache;
  mDrawableFactories=drawableFactories;
  mDebugOverlayEnabledSupplier=debugOverlayEnabledSupplier;
}
