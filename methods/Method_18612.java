private @Nullable AnimationBinding createAnimationsForTransitionSet(TransitionSet transitionSet){
  final ArrayList<Transition> children=transitionSet.getChildren();
  final ArrayList<AnimationBinding> createdAnimations=new ArrayList<>();
  for (int i=0, size=children.size(); i < size; i++) {
    final AnimationBinding animation=createAnimationsForTransition(children.get(i));
    if (animation != null) {
      createdAnimations.add(animation);
    }
  }
  if (createdAnimations.isEmpty()) {
    return null;
  }
  return transitionSet.createAnimation(createdAnimations);
}
