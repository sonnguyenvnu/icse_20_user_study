@Override AnimationBinding createAnimation(List<AnimationBinding> children){
  return new SequenceBinding(children);
}
