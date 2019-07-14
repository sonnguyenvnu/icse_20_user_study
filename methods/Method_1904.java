private CachingStrategyEntry createKeepLastCachingStrategy(){
  return new CachingStrategyEntry(){
    @Override public BitmapFrameCache createBitmapFrameCache(){
      return new KeepLastFrameCache();
    }
    @Override public int getTitleResId(){
      return R.string.cache_keep_last;
    }
  }
;
}
