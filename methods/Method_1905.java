private AnimatedFrameCache createAnimatedFrameCache(){
  return new AnimatedFrameCache(new SimpleCacheKey("Sample"),Fresco.getImagePipelineFactory().getBitmapCountingMemoryCache());
}
