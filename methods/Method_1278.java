public PipelineDraweeControllerBuilder setCustomDrawableFactories(DrawableFactory... drawableFactories){
  Preconditions.checkNotNull(drawableFactories);
  return setCustomDrawableFactories(ImmutableList.of(drawableFactories));
}
