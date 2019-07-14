public SpriteAnimatorBuilder easeInOut(float... fractions){
  interpolator(KeyFrameInterpolator.easeInOut(fractions));
  return this;
}
