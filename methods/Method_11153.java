public static KeyFrameInterpolator easeInOut(float... fractions){
  KeyFrameInterpolator interpolator=new KeyFrameInterpolator(Ease.inOut());
  interpolator.setFractions(fractions);
  return interpolator;
}
