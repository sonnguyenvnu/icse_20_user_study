@Override public ValueAnimator onCreateAnimation(){
  float fractions[]=new float[]{0f,1f};
  return new SpriteAnimatorBuilder(this).scale(fractions,0f,1f).alpha(fractions,255,0).duration(1000).easeInOut(fractions).build();
}
