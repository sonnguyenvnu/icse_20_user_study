@Nullable public Transition getExitTransition(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush){
  if (isPush) {
    return new Explode();
  }
 else {
    return new Slide(Gravity.BOTTOM);
  }
}
