@Override @Nullable public Transition getSharedElementTransition(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush){
  return new TransitionSet().addTransition(new ChangeBounds()).addTransition(new ChangeClipBounds()).addTransition(new ChangeTransform());
}
