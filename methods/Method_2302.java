private static VitoDrawableFactory createDefaultDrawableFactory(Resources resources){
  return new ArrayVitoDrawableFactory(new BitmapDrawableFactory(resources),new DrawableFactoryWrapper(Fresco.getImagePipelineFactory().getAnimatedDrawableFactory(null)));
}
