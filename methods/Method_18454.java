@Override AnimationBinding createAnimation(List<AnimationBinding> children){
  return new ParallelBinding(mStaggerMs,children);
}
