@Override @Nullable public Transition getEnterTransition(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush){
  if (isPush) {
    return new Slide(Gravity.BOTTOM);
  }
 else {
    return new Explode();
  }
}
