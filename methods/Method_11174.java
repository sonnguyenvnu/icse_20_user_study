@Override public ValueAnimator onCreateAnimation(){
  float fractions[]=new float[]{0f,1f};
  return new SpriteAnimatorBuilder(this).rotate(fractions,0,360).duration(2000).interpolator(new LinearInterpolator()).build();
}
