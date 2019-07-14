private @NonNull AnimatorSet lazyHeartCrossFadeAnimation(){
  if (this.heartsAnimation == null) {
    this.heartsAnimation=AnimationUtils.INSTANCE.crossFadeAndReverse(this.heartOutline,this.heartFilled,400L);
  }
  return this.heartsAnimation;
}
