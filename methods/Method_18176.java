@Override AnimationBinding createAnimation(List<AnimationBinding> children){
  if (children.size() != 1) {
    throw new IllegalArgumentException("DelayTransitionSet is expected to have exactly one child, provided=" + children);
  }
  return new DelayBinding(mDelayMs,children.get(0));
}
