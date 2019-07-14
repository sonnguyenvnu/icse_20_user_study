private CachingStrategyEntry createNoOpCachingStrategy(){
  return new CachingStrategyEntry(){
    @Override public BitmapFrameCache createBitmapFrameCache(){
      return new NoOpCache();
    }
    @Override public int getTitleResId(){
      return R.string.cache_noop;
    }
  }
;
}
