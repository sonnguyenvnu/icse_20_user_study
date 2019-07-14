@Override public Animator onDisappear(@NonNull ViewGroup sceneRoot,@NonNull final View view,@Nullable TransitionValues startValues,@Nullable TransitionValues endValues){
  return createAnimation(view,1f,mDisappearedScale,startValues);
}
