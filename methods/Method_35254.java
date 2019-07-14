@Override @NonNull protected Transition getTransition(@NonNull final ViewGroup container,@Nullable final View from,@Nullable final View to,boolean isPush){
  Transition backgroundFade=new Fade();
  backgroundFade.addTarget(R.id.dialog_background);
  Transition fabTransform=new FabTransform(ContextCompat.getColor(container.getContext(),R.color.colorAccent),R.drawable.ic_github_face);
  TransitionSet set=new TransitionSet();
  set.addTransition(backgroundFade);
  set.addTransition(fabTransform);
  return set;
}
