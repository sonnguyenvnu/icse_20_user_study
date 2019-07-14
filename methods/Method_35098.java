@Override @NonNull protected Transition getTransition(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush){
  return new AutoTransition();
}
