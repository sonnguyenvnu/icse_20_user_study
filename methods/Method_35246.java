@Override public void configureSharedElements(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush){
  for (  String name : sharedElementNames) {
    addSharedElement(name);
  }
}
