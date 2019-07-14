private ExperimentalBitmapAnimationDrawableFactory createDrawableFactory(){
  Supplier<Integer> cachingStrategySupplier=new Supplier<Integer>(){
    @Override public Integer get(){
      return ExperimentalBitmapAnimationDrawableFactory.CACHING_STRATEGY_FRESCO_CACHE_NO_REUSING;
    }
  }
;
  final SerialExecutorService serialExecutorServiceForFramePreparing=new DefaultSerialExecutorService(mExecutorSupplier.forDecode());
  Supplier<Integer> numberOfFramesToPrepareSupplier=new Supplier<Integer>(){
    @Override public Integer get(){
      return NUMBER_OF_FRAMES_TO_PREPARE;
    }
  }
;
  return new ExperimentalBitmapAnimationDrawableFactory(getAnimatedDrawableBackendProvider(),UiThreadImmediateExecutorService.getInstance(),serialExecutorServiceForFramePreparing,RealtimeSinceBootClock.get(),mPlatformBitmapFactory,mBackingCache,cachingStrategySupplier,numberOfFramesToPrepareSupplier);
}
