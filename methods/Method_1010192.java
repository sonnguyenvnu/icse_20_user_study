public PostponedReference register(@NotNull PostponedReference ref){
  myPostponedRefs.add(ref);
  return ref;
}
